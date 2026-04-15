package edu.ntnu.idi.idatt.core.security;

import edu.ntnu.idi.idatt.core.classroom.entity.Classroom;
import edu.ntnu.idi.idatt.core.classroom.repository.ClassroomRepository;
import edu.ntnu.idi.idatt.core.user.entity.Pupil;
import edu.ntnu.idi.idatt.core.user.entity.Teacher;
import edu.ntnu.idi.idatt.core.user.entity.User;
import edu.ntnu.idi.idatt.core.user.repository.PupilRepository;
import edu.ntnu.idi.idatt.core.user.repository.TeacherRepository;
import edu.ntnu.idi.idatt.core.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Reusable same-school authorization checks for resources tied to a school.
 */
@Component("schoolScope")
public class SchoolScopeAuthorization {

  private final UserRepository userRepository;
  private final TeacherRepository teacherRepository;
  private final PupilRepository pupilRepository;
  private final ClassroomRepository classroomRepository;

  public SchoolScopeAuthorization(
      UserRepository userRepository,
      TeacherRepository teacherRepository,
      PupilRepository pupilRepository,
      ClassroomRepository classroomRepository
  ) {
    this.userRepository = userRepository;
    this.teacherRepository = teacherRepository;
    this.pupilRepository = pupilRepository;
    this.classroomRepository = classroomRepository;
  }

  /**
   * Checks whether the authenticated user may access a school by school id.
   *
   * @param authentication current authenticated user
   * @param schoolId target school id
   * @return true when the user belongs to the target school
   */
  public boolean canAccessSchool(Authentication authentication, Long schoolId) {
    Long userSchoolId = getAuthenticatedUserSchoolId(authentication);
    return userSchoolId != null && userSchoolId.equals(schoolId);
  }

  /**
   * Checks whether the authenticated user may access a teacher by teacher id.
   *
   * @param authentication current authenticated user
   * @param teacherId target teacher id
   * @return true when both users belong to the same school
   */
  public boolean canAccessTeacher(Authentication authentication, Long teacherId) {
    Long userSchoolId = getAuthenticatedUserSchoolId(authentication);
    if (userSchoolId == null || teacherId == null) {
      return false;
    }

    return teacherRepository.findById(teacherId)
        .map(Teacher::getSchool)
        .map(school -> school.getId())
        .filter(userSchoolId::equals)
        .isPresent();
  }

  /**
   * Checks whether the authenticated user may access a pupil by pupil id.
   *
   * @param authentication current authenticated user
   * @param pupilId target pupil id
   * @return true when the pupil belongs to the same school as the user
   */
  public boolean canAccessPupil(Authentication authentication, Long pupilId) {
    Long userSchoolId = getAuthenticatedUserSchoolId(authentication);
    if (userSchoolId == null || pupilId == null) {
      return false;
    }

    return pupilRepository.findById(pupilId)
        .map(Pupil::getClassroom)
        .map(Classroom::getSchool)
        .map(school -> school.getId())
        .filter(userSchoolId::equals)
        .isPresent();
  }

  /**
   * Checks whether the authenticated user may access a classroom by classroom id.
   *
   * @param authentication current authenticated user
   * @param classroomId target classroom id
   * @return true when the classroom belongs to the same school as the user
   */
  public boolean canAccessClassroom(Authentication authentication, Long classroomId) {
    Long userSchoolId = getAuthenticatedUserSchoolId(authentication);
    if (userSchoolId == null || classroomId == null) {
      return false;
    }

    return classroomRepository.findById(classroomId)
        .map(Classroom::getSchool)
        .map(school -> school.getId())
        .filter(userSchoolId::equals)
        .isPresent();
  }

  private Long getAuthenticatedUserSchoolId(Authentication authentication) {
    Long userId = extractAuthenticatedUserId(authentication);
    if (userId == null) {
      return null;
    }

    return userRepository.findById(userId)
        .map(this::extractSchoolId)
        .orElse(null);
  }

  private Long extractSchoolId(User user) {
    if (user instanceof Teacher teacher && teacher.getSchool() != null) {
      return teacher.getSchool().getId();
    }

    if (user instanceof Pupil pupil
        && pupil.getClassroom() != null
        && pupil.getClassroom().getSchool() != null) {
      return pupil.getClassroom().getSchool().getId();
    }

    return null;
  }

  private Long extractAuthenticatedUserId(Authentication authentication) {
    if (authentication == null || authentication.getName() == null) {
      return null;
    }

    try {
      return Long.parseLong(authentication.getName());
    } catch (NumberFormatException exception) {
      return null;
    }
  }
}
