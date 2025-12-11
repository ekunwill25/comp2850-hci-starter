# Pilot 2 Notes

**Session ID**: P2_2222  
**Date**: 2025-12-09  
**Time**: 13:22–13:38  
**Variant**: Keyboard-only (HTMX on)  
**Facilitator**: William Ekundayo (201843237)  
**Observer**: N/A  
**Consent confirmed**: Yes  

---

## Pre-Session

- Participant asked if mouse was allowed; reminded keyboard-only requirement.
- JS mode ON confirmed; Tab order tested briefly.

---

## Task 1 (T1_add): Add Task

**Start time**: 13:23:10  
**End time**: 13:23:16  
**Duration**: 6s  
**Outcome**: Success  

**Observations**:
- 13:23 — Participant tabbed through fields smoothly.
- 13:23 — Quote: "Okay, this part is easy enough."
- 13:23 — Facilitator noted confident typing and submission.

**Issues**:
- None; keyboard workflow worked as intended.

**Success criteria met**: Yes — task appeared correctly with no errors.

---

## Task 2 (T2_edit): Edit Task

**Start time**: 13:24:00  
**End time**: 13:24:15  
**Duration**: 15s  
**Outcome**: Success-with-errors  

**Observations**:
- 13:24 — Participant tabbed to wrong icon first.
- 13:24 — Quote: "Wait… which one is edit? They look the same."
- 13:24 — Initial submit caused validation error (blank title).

**Issues**:
- Edit affordance unclear for non-visual exploration.  
- Validation error **not announced** — SR pilot issue mirrored.

**Success criteria met**: Partial — completed after correction.

---

## Task 3 (T3_complete): Mark Complete

**Start time**: 13:25:40  
**End time**: 13:25:42  
**Duration**: 2s  
**Outcome**: Success  

**Observations**:
- 13:25 — Participant used Spacebar to toggle checkbox successfully.
- 13:25 — Quote: "Nice, that was straightforward."

**Issues**:
- None.

**Success criteria met**: Yes.

---

## Task 4 (T4_delete): Delete Task

**Start time**: 13:26:20  
**End time**: 13:26:23  
**Duration**: 3s  
**Outcome**: Success  

**Observations**:
- 13:26 — Participant pressed Enter on delete icon.
- 13:26 — Quote: "It’s gone already? No confirmation?"
- 13:26 — Participant seemed surprised by immediate deletion.

**Issues**:
- No confirmation.  
- Keyboard-only users get *zero* deletion feedback.

**Success criteria met**: Partial — task deleted but confusing.

---

## Post-Session Debrief (5 min)

**Subjective ratings**:
- Overall confidence: 3 / 5  
- Ease of use: 3 / 5  
- Would recommend: 3 / 5  

**Open questions**:

1. *"What was most confusing or frustrating?"*  
   - Response: "Edit and delete icons were too similar."

2. *"What worked well?"*  
   - Response: "Keyboard navigation mostly made sense."

3. *"Any accessibility barriers?"*  
   - Response: "Focus was unclear sometimes; lost track of where I was."

**Participant suggestions**:
- "Add keyboard focus outlines so I know where I am."

---

## Facilitator Reflection

- Keyboard navigation exposed issues hidden from mouse users.
- Missing focus outlines and silent validation errors are major barriers.
- Data quality good; timestamps align with server logs.

---

## Evidence Links

**Quantitative data**: `04-results.csv` lines 3–7 (session_id=P2_2222)  
**Screenshots**:  
- `t2-validation-error.png`  
- `t4-delete-no-confirm.png`  
- `focus-missing.png`  

**Backlog items**:  
- wk9-01 (Validation errors not announced)  
- wk9-02 (Edit affordance unclear)  
- wk9-05 (Missing visible focus indicator)
