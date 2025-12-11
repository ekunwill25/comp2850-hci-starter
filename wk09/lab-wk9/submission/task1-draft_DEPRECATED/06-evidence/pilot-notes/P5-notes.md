# Pilot 5 Notes

**Session ID**: P5_5555  
**Date**: 2025-12-09  
**Time**: 14:30–14:55  
**Variant**: Screen reader (NVDA + keyboard)  
**Facilitator**: William Ekundayo  
**Observer**: N/A  
**Consent confirmed**: Yes  

---

## Pre-Session

- NVDA running and speech rate adjusted.
- Participant familiar with screen reader shortcuts.

---

## Task 1 (T1_add): Add Task

**Start time**: 14:31:10  
**End time**: 14:31:20  
**Duration**: 10s  
**Outcome**: Success  

**Observations**:
- 14:31 — NVDA read input label correctly.
- 14:31 — Quote: “That added… I think?”

**Issues**:
- Status message *not announced* — key finding (F3).

**Success criteria met**: Partial — task added but feedback missing.

---

## Task 2 (T2_edit): Edit Task

**Start time**: 14:32:00  
**End time**: 14:32:25  
**Duration**: 25s  
**Outcome**: Success-with-errors  

**Observations**:
- 14:32 — Participant tabbed repeatedly to find edit control.
- 14:32 — Quote: “Icons don’t tell me what they are.”
- 14:32 — Blank submit triggered validation error, not announced.

**Issues**:
- Missing accessible names for edit icons.  
- Validation errors silent → WCAG 4.1.3 failure.

**Success criteria met**: Partial.

---

## Task 3 (T3_complete): Mark Complete

**Start time**: 14:33:40  
**End time**: 14:33:47  
**Duration**: 7s  
**Outcome**: Success  

**Observations**:
- 14:33 — “Checkbox is labelled correctly — good.”

**Issues**:
- No announcement that status changed.

**Success criteria met**: Yes, but feedback lacking.

---

## Task 4 (T4_delete): Delete Task

**Start time**: 14:34:30  
**End time**: 14:34:38  
**Duration**: 8s  
**Outcome**: Success  

**Observations**:
- 14:34 — Quote: “It vanished… was that supposed to happen?”
- 14:34 — No SR feedback after deletion.

**Issues**:
- No confirmation, no live region update.

**Success criteria met**: Partial.

---

## Post-Session Debrief

**Subjective ratings**:
- Overall confidence: 2 / 5  
- Ease of use: 2 / 5  
- Would recommend: 2 / 5  

**Open questions**:

1. *Most confusing?*  
   - “I couldn’t tell when actions succeeded.”

2. *What worked well?*  
   - “Form labels were mostly accessible.”

3. *Any accessibility barriers?*  
   - “Lots — no announcements, unclear icons, disappearing tasks.”

**Participant suggestions**:
- “Add ARIA labels, status announcements, and confirmations.”

---

## Facilitator Reflection

- Critical accessibility gaps confirmed (F3, F5).  
- Feedback assumptions based on visuals fail completely for SR users.  
- This pilot produced highest-impact data.

---

## Evidence Links

**Quantitative data**: `04-results.csv` lines 18–22 (session_id=P5_5555)  
**Screenshots**:  
- `t2-validation-error-no-announce.png`  
- `t4-delete-silent.png`  
- `sr-missing-labels.png`  

**Backlog items**:  
- wk9-01, wk9-05, wk9-06  
