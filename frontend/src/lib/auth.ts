export type AuthRole = 'TEACHER' | 'PUPIL'

export interface AuthUser {
  id: number
  username: string
  firstName: string
  lastName: string
  schoolId: number
  schoolName: string
  classroomId: number | null
  classroomName: string | null
}

export interface AuthSession {
  accessToken: string
  refreshToken: string
  role: AuthRole
  user: AuthUser
}

export function getLandingPath(role: AuthRole) {
  return role === 'TEACHER' ? '/dashboard' : '/game'
}
