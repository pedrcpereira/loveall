# loveall
Multi-tenant management tool for small sports schools: courts, coaches, students, classes, attendance and fees, with each school's data kept separate.

## Tech choices

- **Package by feature**: this gives the project more cohesion if compared to package-by-layer. Even though this is a smaller project, this will let everything about one feature sit together.
- **Ids in URLs**: UUIDv7, which gives a nice mix between performance and security, preventing guessing of other schools' ids with a sequential id.
- **Email uniqueness ignoring case**: emails will be normalized to lower case in Java before saving and querying.
- **Moments in time**: `timestamptz` in the database and `Instant` in Java.