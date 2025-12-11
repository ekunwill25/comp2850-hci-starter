# Redesign Brief — Task 2: Inclusive Redesign

**Author**: William Ekundayo
**Date**: [2025-12-10]
**Version**: [Draft]

---

## Problem

**1-2 sentences grounded in Week 9 analysis + Week 7 audit**:

Validation and success messages on the edit task form (T2) were not announced to screen-reader users, causing confusion, slower task completion, and silent failures. Week 9 pilots showed 2/5 participants (keyboard-only and SR variants) unable to detect blank-title errors, confirming WCAG 4.1.3 and 3.3.1 violations.

**Evidence**:
- Week 9 Lab 2: `05-findings.md` (Finding A1)
- Week 9 metrics: `04-results.csv` (session IDs: P2_2222, P3_3333)
- Week 7 audit: `lab-w7/a11y/audit-template.md` issue [#3]

**WCAG Violations**:
- **4.1.3 Status Messages (AA)**: Status changes must be announced to assistive tech
- **3.3.1 Error Identification (A)**: Errors must be identified and described in text

---

## Goal

**Measurable outcome target**:

| Metric | Baseline (Week 9) | Target (Week 10) | Rationale |
|--------|------------------|------------------|-----------|
| **Error rate** (T2) | 33% (2/6 attempts) | ≤ 10% (1/10 attempts) | Reduce validation errors by improving feedback |
| **Completion rate** (T2) | 80% (4/5 participants) | 100% (5/5 participants) | All participants complete task successfully |
| **Median time** (T2) | 1400ms | ≤ 1200ms | Faster error correction with accessible feedback |
| **SR accessibility** | 0% pass (errors not announced) | 100% pass (errors announced + linked) | WCAG 4.1.3 compliance |

**Success criteria**:
- [/] Error rate reduced to ≤ 10%
- [/] Completion rate = 100%
- [/] All SR users can detect and correct errors independently
- [/] No regression in existing functionality

---

## Inclusion Impact

**Who benefits from this fix**:

| User Group | Current Barrier | After Fix | Impact Level |
|-----------|----------------|-----------|--------------|
| **Screen reader users** | Validation errors not announced → cannot detect or correct | Errors announced via `role="alert"` + `aria-describedby` | 🔴 **Critical** (blocks task) |
| **Keyboard-only users** | Error summary not keyboard-focusable in no-JS mode | Error summary receives focus on load (`tabindex="-1"`) | 🟠 **High** (delays task) |
| **Low vision users** | Error text too small, poor contrast | Larger text (16px), contrast 7:1 (AAA) | 🟡 **Medium** (improves readability) |
| **Cognitive load** | Multiple errors shown without clear action | Inline errors + summary with "Skip to first error" link | 🟡 **Medium** (reduces confusion) |

**Overall inclusion score**: **Critical** — Fix addresses WCAG AA violations affecting SR and keyboard users.

---

## Societal Impact (LO10)

**Who is excluded by the current design?**
- People who rely on screen readers for daily navigation: blind users, low-vision users, users with cognitive disabilities.
- Disabled students who depend on accessible tools for coursework/task management.

**Broader societal implications**:
- Inaccessible validation patterns mirror common barriers in job applications, government forms, banking portals, and healthcare booking systems.
- Poor error feedback reinforces systemic inequality by placing responsibility on disabled users rather than poor design.

**How does this fix address systemic barriers?**
- Implements universal design: robust, testable, equitable feedback mechanisms for all users—not separate “accommodations.”
- Reduces error rates for everyone, not only disabled users.

**Ethical considerations**:
- Removes situations where SR users must reveal sensitive mistakes to others—protecting privacy and autonomy.
- Ensures fairness and equal access by providing the same actionable information to all users.

**Long-term impact**:
- If widely adopted, accessible validation would dramatically improve digital independence for millions of disabled people.
- Reduces educational and workplace inequalities tied to inaccessible digital systems

**References**:
- [Link to Week 7 Lab 1 ethics reflection if relevant]
- [GOV.UK: Accessibility and Equality](https://www.gov.uk/guidance/equality-act-2010-guidance)
- [WebAIM: The Social Model of Disability](https://webaim.org/articles/evaluationguide/)

---

## Changes (Server-First + No-JS Parity)

### Change 1: Add `role="alert"` to Error Messages

**Before**:
```html
<span class="error">Title is required</span>
```

**After**:
```html
<span class="error" role="alert">Title is required</span>
```

**Rationale**: Screen readers automatically announce `role="alert"` content when it appears.

**No-JS impact**: Works identically (role attribute is static HTML).

---

### Change 2: Link Errors to Inputs via `aria-describedby`

**Before**:
```html
<input type="text" id="title" name="title">
<span class="error">Title is required</span>
```

**After**:
```html
<input type="text" id="title" name="title" aria-describedby="title-hint title-error">
<span id="title-hint">Max 100 characters</span>
<span id="title-error" class="error" role="alert">Title is required</span>
```

**Rationale**: SR users hear error message when focused on input (context awareness).

**No-JS impact**: Works identically (ARIA attributes are static HTML).

---

### Change 3: Focus Error Summary in No-JS Mode

**Before** (no-JS):
```kotlin
// POST-Redirect-GET pattern
call.respondRedirect("/tasks/${id}/edit?error=blank_title")
```

**After** (no-JS):
```kotlin
// POST-Redirect-GET + focus hint
call.respondRedirect("/tasks/${id}/edit?error=blank_title&focus=error-summary")
```

**Template** (`edit.peb`):
```html
<div id="error-summary" tabindex="-1" {% if request.getParameter("focus") == "error-summary" %}autofocus{% endif %}>
  <h2>There is a problem</h2>
  <ul>
    <li><a href="#title">Title is required</a></li>
  </ul>
</div>
```

**Rationale**: After redirect, focus moves to error summary so keyboard users don't need to tab through entire page.

**HTMX path**: Unchanged (OOB swap already handles focus management).

---

### Change 4: Improve Error Text Contrast and Size

**Before**:
```css
.error {
  color: #d32f2f; /* Contrast ratio 3.5:1 (FAILS AA) */
  font-size: 14px;
}
```

**After**:
```css
.error {
  color: #b71c1c; /* Contrast ratio 7.1:1 (PASSES AAA) */
  font-size: 16px;
  font-weight: 600;
}
```

**Rationale**: WCAG 1.4.3 (Contrast AA) requires 4.5:1 for text. Exceeding to AAA (7:1) benefits low vision users.

---

## Acceptance Tests

**All tests must PASS before submission**:

| Test | Method | Expected Outcome | Status |
|------|--------|------------------|--------|
| **Keyboard-only path** | Tab through form, trigger error, correct | Focus moves to error summary, all errors reachable, task completable | [ ] PASS / [ ] FAIL |
| **SR error announcements** | Submit blank form with NVDA/Orca | SR announces "Title is required" via `role="alert"`, reads error when focused on input | [ ] PASS / [ ] FAIL |
| **JS-off parity** | Disable JS, submit blank form, verify error summary focused | Full page reload, error summary receives focus, task completable | [ ] PASS / [ ] FAIL |
| **Contrast** | Use Colour Contrast Analyser on error text | Contrast ≥ 7:1 (AAA) | [ ] PASS / [ ] FAIL |
| **Zoom** | View at 200% zoom (Chrome DevTools) | All text readable, no horizontal scroll | [ ] PASS / [ ] FAIL |
| **Regression: existing functionality** | Complete all 4 tasks (T1, T2, T3, T4) with HTMX + no-JS | All tasks work identically to Week 9 baseline | [ ] PASS / [ ] FAIL |

---

## Measurement Plan

### Phase 1: Regression Testing (Week 10 Lab 2, first 30 min)

**Method**: Manual testing with keyboard, SR, no-JS

**Checklist**: `02-a11y-regression-checklist.csv` (complete all rows)

**Pass criteria**: All checks = PASS (no regressions)

---

### Phase 2: Re-Pilots (Week 10 Lab 2, 30 min)

**Method**: Repeat Week 9 protocol with n=2 participants

**Variants**:
- 1× keyboard-only
- 1× no-JS

**Tasks**: Focus on T2 (edit task) — the fixed flow

**Metrics captured**:
- Completion rate (should be 100%)
- Error rate (should be ≤ 10%)
- Median time (should be ≤ 1200ms)
- SR announcement success (should be 100%)

**Data location**: `06-metrics/post/postchange.csv`

---

### Phase 3: Before/After Comparison (Week 10 Lab 2, last 20 min)

**Compare**:
- `06-metrics/pre/analysis.csv` (Week 9 baseline)
- `06-metrics/post/postchange.csv` (Week 10 post-fix)

**Metrics to compare**:

| Task | Metric | Pre | Post | Δ | Goal Met? |
|------|--------|-----|------|---|-----------|
| T2 | median_ms | 1400 | [TBD] | [TBD] | [ ] Yes / [ ] No |
| T2 | error_rate | 33% | [TBD] | [TBD] | [ ] Yes / [ ] No |
| T2 | completion_rate | 80% | [TBD] | [TBD] | [ ] Yes / [ ] No |
| T2 | sr_accessibility | 0% | [TBD] | [TBD] | [ ] Yes / [ ] No |

**Document in**: `03-before-after-summary.md`

---

## Timeline

| Activity | Duration | Deliverable |
|----------|----------|-------------|
| Implement fixes | 40 min | Code changes in Routes.kt, templates, CSS |
| Regression testing | 30 min | Completed `02-a11y-regression-checklist.csv` |
| Re-pilots (n=2) | 30 min | Post-change metrics in `06-metrics/post/postchange.csv` |
| Document code diffs | 20 min | Screenshots/snippets in `04-key-diffs.md` |
| Package Task 2 | 20 min | All files in `gradescope/task2/` ready for submission |

**Total**: 2h 20min (fits in Week 10 Lab 2 + independent study)

---

## Risk Assessment

| Risk | Impact | Mitigation |
|------|--------|------------|
| Fix breaks existing HTMX path | High | Comprehensive regression testing before re-pilots |
| Re-pilots show no improvement | High | Test fixes manually before inviting participants |
| No-JS error focus doesn't work | Medium | Test PRG pattern with `autofocus` attribute fallback |
| SR announces errors too loudly | Low | Use `aria-live="polite"` instead of `"assertive"` |

---

## Learning Outcomes Addressed

- **LO4**: Evaluate for accessibility — evidenced by regression testing checklist
- **LO6**: Apply iterative design — evidenced by redesign → re-verification cycle
- **LO9**: Apply inclusive design — evidenced by WCAG 2.2 AA compliant fixes
- **LO12**: Demonstrate professionalism — evidenced by evidence chains linking problem → fix → verification

---

## References

- **WCAG 2.2 Quick Reference**: https://www.w3.org/WAI/WCAG22/quickref/
- **WebAIM: Creating Accessible Forms**: https://webaim.org/techniques/forms/
- **GOV.UK: Error Message Patterns**: https://design-system.service.gov.uk/components/error-message/
- **Nielsen: Error Message Guidelines**: https://www.nngroup.com/articles/error-message-guidelines/

---

**Status**: [Draft / Ready for implementation / Implementation complete / Verified]
**Next steps**: [What needs to happen next?]
