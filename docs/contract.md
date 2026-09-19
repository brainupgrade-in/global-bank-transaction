# What we depend on in global-bank-account

| We call | For |
|---|---|
| `POST /api/v1/postings` | One posting per payroll item |
| `GET /api/v1/accounts/{id}/balance` | Not used yet — reserved for the pre-flight funding check |

## The mirrored types

`client/PostingRequest` and `client/PostingResponse` are hand-written mirrors of the producer's
`api/PostingRequest` and `api/PostingResponse`. There is no code generation today; the cost of
keeping them in step by hand is accepted, and the discipline that makes it safe is ADR-009 in the
producer repository: **producer merges first, consumer second, pull requests linked.**

`PostingResponse` is annotated `@JsonIgnoreProperties(ignoreUnknown = true)` so an additive field on
the producer side cannot break us at runtime. That protects reads. It does **not** protect writes —
a field the producer starts requiring will fail here, which is why the sequencing rule exists.

## Failure semantics we rely on

- A rejected posting returns a 4xx with an RFC 9457 problem body whose `title` is the producer's
  stable `reason` string. We log it and continue with the rest of the batch.
- The posting API makes no outbound calls, so a timeout here means the account service itself
  is unhealthy — there is no downstream to blame.
