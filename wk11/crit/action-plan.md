# Action Plan from Studio Crit

## High Priority (Do Before Submission)

- [ ] **Focus management after delete**
  - Issue: Focus lost after task removal (P2 & P5 confusion)
  - Fix: Programmatically set focus to next item or "Add Task"
  - Effort: ~30 minutes
  - Deadline: 2025-12-12

- [ ] **Screen reader announcement improvements**
  - Issue: SR users unaware of success/failure states
  - Fix: Add role="alert", aria-live="assertive", aria-describedby
  - Effort: ~25 minutes
  - Deadline: 2025-12-12

- [ ] **Document screen reader testing**
  - Add NVDA + no-JS testing notes into evidence folder
  - Effort: ~20 minutes
  - Deadline: 2025-12-12

---

## Medium Priority (If Time Permits)

- [ ] **Delete confirmation page (no-JS mode)**
  - Would reduce accidental deletions (raised by P4 & P5)
  - Helps meet WCAG 3.3.4 Error Prevention
  - Effort: ~1 hour

- [ ] **Add helper text for filter**
  - Clarify auto-filtering interaction
  - Effort: ~10 min

---

## Low Priority

- [ ] **Progress indicator for long operations**
  - Nice-to-have for future enhancements
  - Not required for WCAG compliance

---

## Not Actioning (With Rationale)

- **Add full undo-history system**
  - Beyond scope, requires substantial backend redesign
  - Not necessary for this assessment
