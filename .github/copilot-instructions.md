# Global Bank Transaction Service — payroll disbursements — working agreement

Turns a payroll batch into one posting per employee by calling `global-bank-account`.
The `payroll` package owns no ledger state. It is a client of the account service.
The rest of this repository (`controller/`, `service/`, `models/`) is the older transfer code; it
uses `double` amounts and predates these rules. New money code follows the rules below.

## Read these before proposing a change

- `docs/contract.md` — what we depend on in the producer, and the rule for changing it together
- The producer's own `docs/adr/` — **our constraints are mostly its constraints**

## Non-negotiables

- **Amounts are `long` minor units**, matching the producer. See the producer's ADR-003.
- **Constructor injection only.** No field injection, no Lombok.
- `PostingRequest` in `client/` is a hand-maintained mirror of the producer's request body.
  It changes **only** in step with a producer change, and the pull requests are linked. See ADR-009
  in the producer repository.
- **A failed item must not fail the batch.** `disburse` records the rejection and continues. Changing
  that to fail-fast would strand a half-disbursed payroll with no record of what landed.
- `clientReference` is composed as `<batchId>-<itemReference>` and must stay stable across retries of
  the same batch — the producer's duplicate suppression keys on it.

## When you are unsure

The producer is the source of truth for the wire contract. Read it rather than guessing the shape,
and do not add a field to `PostingRequest` that the producer does not accept.
