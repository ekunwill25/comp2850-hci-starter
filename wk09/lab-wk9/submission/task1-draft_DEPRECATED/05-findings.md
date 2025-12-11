# Task 1: Findings & Evidence Chains

**Purpose**: Synthesize pilot data into actionable findings with evidence chains linking raw data to backlog items.

---

## Executive Summary

**Pilots conducted**: [5] participants
**Date range**: [2025-11-29 to 2025-12-10]
**Variants tested**: Standard (HTMX), keyboard-only, no-JS, [screen reader]

**Key findings**:
1. SR users unaware of changes (WCAG 4.1.3)
2. navigation difficulty for P2 & P5 (WCAG 2.4.7)
3. accidental deletions (UX risk, inclusion impact)

**Recommended fixes for Week 10**: Add delete confirmation dialog + Improve keyboard focus outline (CSS)

---

## 1. Quantitative Results

### 1.1 Completion Rates

| Task | Code | Completion | Notes |
|------|------|-----------|-------|
| [Task name] | T1 | [5] / [5] ([100%]) | None|
| [Task name] | T2 | [5] / [5] ([100%]) | Slow for P2,P5 |
| [Task name] | T3 | [5] / [5] ([100%]) | None |
| [Task name] | T4 | [5] / [5] ([100%]) | Sudden Removal |

**Analysis**: Tasks were completed by all 5 participants, but T2 edits were slower and T4 deletions caused uncertainty for some participants.

**Evidence**:
- `04-results.csv` lines [X-Y]: [Session IDs] with `step=fail` or incomplete task sequences
- `06-evidence/pilot-notes/P[X]-notes.md` line [Y]: [Quote about giving up]

---

### 1.2 Task Completion Times

**Median times** (success only):

| Task | Code | Median (ms) | Median (s) | MAD (ms) | Range | n |
|------|------|------------|------------|---------|-------|---|
| Add | T1 | [910] | [0.9]s | [Low] | [0.8]s–[1.1]s | [1] |
| Edit | T2 | [1465] | [1.4]s | [Medium] | [1.4]s–[1.5]s | [2] |
| Complete | T3 | [590] | [0.6]s | [Low] | [0.5]s–[0.7]s | [1] |
| Delete | T4 | [703] | [0.7]s | [Low] | [0.7]s–[0.8]s | [1] |

**Analysis**: T2/Edit was the slowest task, mainly for keyboard-only and screen reader participants, due to lack of clear feedback and unclear focus.

**Evidence**:
- `04-results.csv`: Filtered `step=success`, calculated median and MAD
- `06-evidence/pilot-notes/`: [References to hesitation, confusion, efficiency]

---

### 1.3 Error Rates

| Task | Code | Validation Errors | Total Attempts | Error Rate | Notes |
|------|------|------------------|----------------|-----------|-------|
| Add | T1 | [0] | [5] | [0%] | [No issues] |
| Edit| T2 | [0] | [5] | [0%] | [Errors not visible to SR] |
| Complete | T3 | [0] | [5] | [0%] | [No issues] |
| Delete | T4 | [0] | [5] | [0%] | Lacked confirmation |

**Analysis**: No validation errors, but SR users failed to perceive success — equivalent usability barrier.

**Evidence**:
- `04-results.csv`: Filtered `step=validation_error`, counted occurrences
- `06-evidence/pilot-notes/`: [Quotes about error messages, confusion]

---

### 1.4 JS-On vs JS-Off Comparison

**Task [4] comparison** (most impacted by JS):

| Variant | Median Time (ms) | n | Notes |
|---------|-----------------|---|-------|
| JS-on (HTMX) | [703] | [1] | [Smooth AJAX updates] |
| JS-off (traditional) | [1406] | [1] | [Full page reloads] |
| **Difference** | [2]× slower | | [Expected due to PRG pattern] |

**Analysis**: Functionality is the same in No-JS, but feedback is weaker and slower.

**Evidence**:
- `04-results.csv`: Filtered `js_mode=off` for session [P3_xxxx]
- `06-evidence/pilot-notes/P3-notes.md`: [Observations about full page reloads]

---

## 2. Qualitative Findings

### 2.1 Accessibility Issues

**Finding A1**: Screen reader users could not detect when edit or add actions succeeded.

**Severity**: High (WCAG [4.1.3] violation)

**Affected users**: People navigating with screen readers

**Evidence chain**:
1. **Raw data**: `04-results.csv` line [18]: Session [P2_2222], task [T2], `step=validation_error`
2. **Observation**: `06-evidence/pilot-notes/P2-notes.md` line [48]: "Quote: Wait… which one is edit? They look the same."
3. **Screenshot**: `06-evidence/screenshots/t2-validation-error.png` showing error without `role="alert"`
4. **WCAG reference**: [4.1.3 Status Messages (AA)] — status changes must be announced

**Impact**:
- **Inclusion**: SR users cannot detect or correct validation errors → task failure
- **Frequency**: [40]% of attempts on Task [2] triggered validation errors
- **Criticality**: Blocks task completion for SR users

**Proposed mitigation**: [Specific fix, e.g., "Add role='alert' to error messages, aria-describedby for input association"]

**Backlog item**: [wk9-01] (see `backlog/backlog.csv`)

---

**Finding A2**: Keyboard focus unclear (WCAG 2.4.7)

[Repeat structure: severity, affected users, evidence chain, impact, mitigation, backlog ref]

---

### 2.2 Usability Issues

**Finding U1**: Keyboard-only users struggled to see which element was currently selected.

**Severity**: Medium (UX issue, not WCAG violation)

**Evidence chain**:
1. **Observation**: `06-evidence/pilot-notes/P1-notes.md` line [X]: "[Quote: 'Is it filtering automatically? I expected a button.']"
2. **Observation**: `06-evidence/pilot-notes/P4-notes.md` line [Y]: "[Quote: 'I typed and then waited... nothing happened']"
3. **Screenshot**: `06-evidence/screenshots/filter-expectation.png`

**Impact**:
- **Inclusion**: Low (all users can eventually figure out)
- **Frequency**: [X] / [n] participants hesitated
- **Criticality**: Delays task but doesn't block

**Proposed mitigation**: [Specific fix, e.g., "Add visible 'Apply Filter' button or help text ('Results update as you type')"]

**Backlog item**: [wk9-02] (see `backlog/backlog.csv`)

---

**Finding U2**: [Next usability issue]

[Repeat structure]

---

### 2.3 Positive Observations

**What worked well** (keep in redesign):

1. Task completion was fast
   - Evidence: `06-evidence/pilot-notes/P1-notes.md` line [120]: "[Good confirmation of progressive enhancement strategy.']"

2. No-JS mode supported full CRUD parity
   - Evidence: `06-evidence/pilot-notes/P2-notes.md` line [118]: "[No-JS still fully functional — parity mostly achieved.']"

3. Form labels accessible
   - Evidence: `04-results.csv`: Session [P3_3333] completed all tasks with `js_mode=off`

---

## 3. Prioritization for Week 10

**Prioritization formula**: `(Impact + Inclusion) – Effort` (all 1–5 scale)

| ID | Issue | Impact | Inclusion | Effort | Score | Priority | Week 10? |
|----|-------|--------|-----------|--------|-------|----------|----------|
| wk9-01 | [Issue 1] | 5 | 5 | 2 | 8 | High | ✅ |
| wk9-02 | [Issue 2] | 4 | 5 | 3 | 6 | High | ✅ |
| wk9-03 | [Issue 3] | 3 | 3 | 1 | 5 | Medium | ⏸ |
| wk9-04 | [Issue 4] | 2 | 2 | 4 | 0 | Low | ❌ |

**Rationale**:
- **Impact**: Severity of problem (5 = blocks task, 1 = minor annoyance)
- **Inclusion**: Affects marginalized users (5 = SR/keyboard only, 1 = all users equally)
- **Effort**: Development + testing time (5 = >4 hours, 1 = <30 min)

**Week 10 focus**: [wk9-01], [wk9-02] — highest inclusion impact + feasible effort

---

## 4. Evidence Chains (Summary)

**Purpose**: Ensure every claim is traceable to data (no assumptions).

**Format**: `Raw data → Observation → Interpretation → Backlog item → Proposed fix`

### Example: Finding A1 (Validation errors not announced)

```
04-results.csv line 127: P2_4d8e,T2_edit,validation_error,blank_title,0,400,on
    ↓
06-evidence/pilot-notes/P2-notes.md line 12: "P2: 'I didn't hear any error message'"
    ↓
Interpretation: SR users cannot detect validation errors (WCAG 4.1.3)
    ↓
backlog/backlog.csv wk9-01: "Add role=alert to error messages"
    ↓
Week 10 fix: Implement aria-live="assertive" + aria-describedby + focus management
```

**All findings must follow this structure** for credibility and transparency.

---

## 5. Limitations & Future Work

### 5.1 Study Limitations

1. **Sample size**: [5] participants (small but typical for formative evaluation—Nielsen's 5-user rule)
2. **Diversity**: Peer-only
3. **Context**: Lab setting (not real-world usage—may miss long-term patterns)
4. **Tasks**: Artificial scenarios (real tasks might reveal different issues)

### 5.2 Data Quality Issues

- No missing data, no crashes
- Minor timing variance due to manual stopwatch checks
- Potential facilitation bias minimized by script use

### 5.3 Future Evaluations

**For Week 10 re-pilots** (post-redesign):
- Focus on fixed issues (A1, A2) to verify improvement
- Use same tasks + protocol for comparability
- Target [n=2] participants (sufficient for regression testing)

**For professional practice**:
- Recruit diverse participants (age, ability, tech literacy)
- Extend to real-world tasks (not lab scenarios)
- Longitudinal studies (usage over weeks/months)

---

## 6. Conclusion

**Summary of key findings**:
1. Missing announcements blocked SR users
2. Unclear focus hindered navigation
3. Delete lacked confirmation and created uncertainty

**Recommended actions**:
- Week 10 Lab 1: Detailed analysis with `Analyse.kt`, prioritise fixes
- Week 10 Lab 2: Implement [2-3] priority fixes, re-verify with regression testing + re-pilots
- Week 11: Present evidence-led redesign in studio critique

**Learning Outcomes addressed**:
- **LO6**: Iterative design (pilot → findings → redesign)
- **LO8**: Evaluation execution (protocol, metrics, analysis)
- **LO11**: Collaboration (peer pilots, observer role)
- **LO12**: Professionalism (consent, evidence chains, honest reporting)

---

## Appendices

### Appendix A: WCAG 2.2 References

- **3.2.1 On Focus (A)**: Focus changes must not trigger unexpected context changes
- **3.3.1 Error Identification (A)**: Errors must be identified and described in text
- **3.3.3 Error Suggestion (AA)**: Error messages should suggest corrections
- **4.1.3 Status Messages (AA)**: Status changes must be announced to assistive tech

See [WCAG 2.2 Quick Reference](https://www.w3.org/WAI/WCAG22/quickref/) for full criteria.

### Appendix B: Definitions

- **MAD** (Median Absolute Deviation): Robust measure of variability (less affected by outliers than standard deviation)
- **PRG** (POST-Redirect-GET): Pattern to prevent form resubmission on browser refresh
- **SR** (Screen Reader): Assistive tech that reads screen content aloud (e.g., NVDA, JAWS, VoiceOver)
- **HTMX**: Library for AJAX updates via HTML attributes (progressive enhancement)

See [Glossary](../../references/glossary.md) for full definitions.

---

**Author**: William Ladepo
**Date**: [2025-12-10]
**Version**: Draft for Task 1 submission
**Next review**: Week 11 (finalize for portfolio)
