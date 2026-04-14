# Backend API-krav — Lineært spill

**Prosjekt:** IDATT2106 Fullstack — Spillplattform  
**Fra:** Frontend-team  
**Til:** Backend/DevOps  
**Stack:** Java 21 + Spring Boot 3 | JWT + Spring Security | JPA/Hibernate  
**Arkitektur:** Modulbasert backend (feature packages)  
**Frontend:** Vue 3 + TypeScript

---

## 1. Autentisering og autorisasjon

### Krav
- JWT-basert autentisering (access + refresh token)
- Roller: `PLAYER`, `ADMIN`
- Innlogget bruker hentes via token
- Refresh-token kan roteres og ugyldiggjøres ved logout

### Endepunkter

| Metode | Sti | Beskrivelse | Roller |
|--------|-----|-------------|--------|
| POST | `/api/auth/register` | Opprett ny spillerkonto | Alle |
| POST | `/api/auth/login` | Innlogging, returnerer JWT-par | Alle |
| POST | `/api/auth/refresh` | Forny access token | Alle |
| POST | `/api/auth/logout` | Invalider refresh token | Alle |
| GET | `/api/auth/me` | Returnerer innlogget brukerprofil | Alle |

### Respons `POST /api/auth/login`
```json
{
  "accessToken": "eyJ...",
  "refreshToken": "eyJ...",
  "user": {
    "id": 17,
    "username": "edvard",
    "email": "edvard@example.com",
    "role": "PLAYER"
  }
}
```

---

## 2. Brukerprofil

### Krav
- CRUD for egen profil (begrenset felter)
- Admin kan hente/liste alle brukere
- `xp` skal legges til i `User`-objektet
- Brukerstatus (`ACTIVE`, `BANNED`) skal støttes

### Endepunkter

| Metode | Sti | Beskrivelse | Roller |
|--------|-----|-------------|--------|
| GET | `/api/users/me` | Hent egen profil | PLAYER, ADMIN |
| PUT | `/api/users/me` | Oppdater egen profil (visningsnavn, e-post) | PLAYER, ADMIN |
| GET | `/api/users/{id}` | Hent bruker på id | ADMIN |
| GET | `/api/users` | Liste brukere (paginert) | ADMIN |
| PATCH | `/api/users/{id}/status` | Endre status | ADMIN |

### Respons `GET /api/users/me`
```json
{
  "id": 17,
  "username": "edvard",
  "email": "edvard@example.com",
  "role": "PLAYER",
  "status": "ACTIVE",
  "xp": 1240,
  "level": 6,
  "createdAt": "2026-02-01T10:20:00Z"
}
```

---

## 3. Progresjon (XP og nivå)

### Krav
- Spillet er lineært: all progresjon styres av XP
- XP tildeles via fullførte oppdrag/kapitler
- Nivå beregnes basert på XP terskler
- Progresjonshendelser logges for historikk og revisjon

### Endepunkter

| Metode | Sti | Beskrivelse | Roller |
|--------|-----|-------------|--------|
| GET | `/api/progression/me` | Hent egen progresjon (xp, level, neste nivå) | PLAYER, ADMIN |
| GET | `/api/progression/me/history` | Hent XP-historikk | PLAYER, ADMIN |
| POST | `/api/progression/me/events` | Registrer progresjonshendelse | PLAYER, ADMIN |
| POST | `/api/admin/users/{id}/xp` | Legg til/trekk fra XP manuelt | ADMIN |

### Request `POST /api/progression/me/events`
```json
{
  "source": "QUEST_COMPLETED",
  "sourceId": "quest-12",
  "xpDelta": 150,
  "metadata": {
    "chapter": 2,
    "difficulty": "NORMAL"
  }
}
```

### Respons `GET /api/progression/me`
```json
{
  "userId": 17,
  "xp": 1240,
  "level": 6,
  "currentLevelMinXp": 1100,
  "nextLevelXp": 1400,
  "xpToNextLevel": 160
}
```

---

## 4. Lineær spillflyt (kapitler og oppdrag)

### Krav
- Bruker kan kun spille oppdrag i riktig rekkefølge
- Neste kapittel/opdrag låses opp ved XP-krav
- Backend verifiserer at oppdrag kan startes/fullføres
- Forsøk på å hoppe over steg returnerer 403

### Endepunkter

| Metode | Sti | Beskrivelse | Roller |
|--------|-----|-------------|--------|
| GET | `/api/campaign/me` | Hent nåværende kapittel, oppdrag og låser | PLAYER, ADMIN |
| GET | `/api/campaign/chapters` | Hent alle kapitler med krav | PLAYER, ADMIN |
| POST | `/api/campaign/quests/{questId}/start` | Start oppdrag hvis krav er oppfylt | PLAYER, ADMIN |
| POST | `/api/campaign/quests/{questId}/complete` | Fullfør oppdrag og tildel XP | PLAYER, ADMIN |

### Respons `GET /api/campaign/me`
```json
{
  "currentChapter": 2,
  "currentQuestId": "quest-12",
  "unlockedQuestIds": ["quest-10", "quest-11", "quest-12"],
  "nextLockedQuest": {
    "id": "quest-13",
    "requiredXp": 1300
  }
}
```

---

## 5. Avatar og kosmetikk

### Krav
- Bruker kan endre avatar med følgende kategorier:
  - `HAT`
  - `SKIN_TONE`
  - `JACKET`
  - `PANTS`
  - `SHOES`
- Kosmetikk representeres som items som maps til enums på avatar
- Kun eide items kan equip-es
- Én aktiv item per kategori

### Endepunkter

| Metode | Sti | Beskrivelse | Roller |
|--------|-----|-------------|--------|
| GET | `/api/avatar/me` | Hent aktiv avatar-konfigurasjon | PLAYER, ADMIN |
| PUT | `/api/avatar/me` | Sett hele avatar-oppsettet | PLAYER, ADMIN |
| PATCH | `/api/avatar/me/equip` | Equip item i kategori | PLAYER, ADMIN |
| GET | `/api/avatar/items` | Hent alle kosmetikk-items (masterdata) | PLAYER, ADMIN |
| GET | `/api/avatar/inventory/me` | Hent spillerens eide kosmetikk-items | PLAYER, ADMIN |
| POST | `/api/avatar/inventory/me/unlock` | Lås opp item (XP/belønning/admin) | PLAYER, ADMIN |

### Request `PATCH /api/avatar/me/equip`
```json
{
  "slot": "HAT",
  "itemCode": "HAT_WIZARD_BLUE"
}
```

### Respons `GET /api/avatar/me`
```json
{
  "userId": 17,
  "equipped": {
    "hat": "HAT_WIZARD_BLUE",
    "skinTone": "SKIN_TONE_MEDIUM",
    "jacket": "JACKET_LEATHER_BLACK",
    "pants": "PANTS_DENIM_DARK",
    "shoes": "SHOES_SNEAKER_WHITE"
  },
  "updatedAt": "2026-04-14T12:20:00Z"
}
```

### Enums (minimum)
```text
AvatarSlot = HAT | SKIN_TONE | JACKET | PANTS | SHOES
UnlockSource = QUEST_REWARD | LEVEL_REWARD | ADMIN_GRANT | SHOP_PURCHASE
ItemRarity = COMMON | RARE | EPIC | LEGENDARY
```

---

## 6. Butikk og opplåsing (valgfritt, men anbefalt)

### Krav
- Kosmetikk kan låses opp via XP-krav eller valuta
- Backend validerer krav før kjøp/opplåsing
- Kjøp logges i historikk

### Endepunkter

| Metode | Sti | Beskrivelse | Roller |
|--------|-----|-------------|--------|
| GET | `/api/shop/items` | Hent tilgjengelige items i butikk | PLAYER, ADMIN |
| POST | `/api/shop/purchase` | Kjøp item | PLAYER, ADMIN |
| GET | `/api/shop/purchases/me` | Kjøpshistorikk | PLAYER, ADMIN |

### Request `POST /api/shop/purchase`
```json
{
  "itemCode": "JACKET_LEATHER_BLACK",
  "currency": "XP"
}
```

---

## 7. Leaderboard og sosial oversikt

### Krav
- Rangering basert på XP
- Topp-liste med paginering
- Egen plassering skal kunne hentes separat

### Endepunkter

| Metode | Sti | Beskrivelse | Roller |
|--------|-----|-------------|--------|
| GET | `/api/leaderboard` | Topp spillere etter XP | PLAYER, ADMIN |
| GET | `/api/leaderboard/me` | Egen plassering | PLAYER, ADMIN |

### Query-parametre `GET /api/leaderboard`
- `page` / `size`
- `season` (valgfri)

---

## 8. Admin-innhold og konfig

### Krav
- Admin kan administrere XP-terskler
- Admin kan administrere kapittel/oppdrag og rewards
- Endringer skal versjoneres

### Endepunkter

| Metode | Sti | Beskrivelse | Roller |
|--------|-----|-------------|--------|
| GET | `/api/admin/config/xp-levels` | Hent nivå-terskler | ADMIN |
| PUT | `/api/admin/config/xp-levels` | Oppdater nivå-terskler | ADMIN |
| POST | `/api/admin/campaign/chapters` | Opprett kapittel | ADMIN |
| PUT | `/api/admin/campaign/chapters/{id}` | Oppdater kapittel | ADMIN |
| POST | `/api/admin/campaign/quests` | Opprett oppdrag | ADMIN |
| PUT | `/api/admin/campaign/quests/{id}` | Oppdater oppdrag | ADMIN |

---

## Tverrgående krav

### Alle endepunkter
- JWT i `Authorization: Bearer <token>` header
- Standard feilrespons: `{ "error": "KODE", "message": "Beskrivelse" }`
- HTTP statuskoder: 200, 201, 400, 401, 403, 404, 409, 500
- Paginering på liste-endepunkter: `?page=0&size=20`

### Domene- og datakrav
- `User` må utvides med minst: `xp`, `level`, `status`
- Avatar-oppsett lagres med enums per slot (hatt, hudfarge, jakke, bukse, sko)
- Spillprogresjon må være deterministisk og lineær
- Alle XP-endringer skal logges med kilde og tidsstempel
- Tidsstempler på entiteter: `createdAt`, `updatedAt`

### Sikkerhet
- Input-validering på alle request DTO-er (Spring Validation)
- Rate limiting på auth og progresjonsendepunkter
- Beskyttelse mot manipulering av XP/events (server-side validering)
- CORS konfigurert for frontend-domene

### Testing (minimum)
- Integrasjonstester for auth-flyt
- Integrasjonstester for lineær progresjon (inkl. blokkering ved feil rekkefølge)
- Tester for XP-beregning og level-up
- Tester for equip-regler (kun eide items, én per slot)
