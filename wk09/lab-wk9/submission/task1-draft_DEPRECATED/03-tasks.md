# Task 1: Evaluation Tasks

**Purpose**: Define the specific tasks participants will perform during usability testing.

---

## Task Design Principles

- **Realistic**: Tasks should reflect real-world use cases
- **Specific**: Clear success criteria (not open-ended)
- **Independent**: Tasks don't depend on each other
- **Measurable**: Can capture time, errors, completion
- **Inclusive**: Consider keyboard-only, screen reader, no-JS access

---

## Task 1 (T1): [Task Title]

### Scenario
> You are organising your coursework and need to quickly add a new reminder to your task list.

### Instructions (read to participant)
> "Add a new task titled ‘COMP2850 lab report’ to your list."

### Success Criteria
- [ ] New task appears in list
- [ ] Title matches exactly “COMP2850 lab report”
- [ ] No validation errors occur


### Expected Duration
**Target**: < [10] seconds

### Known Risks
- Blank submission may trigger validation error
- Status message may not announce correctly for screen readers

---

## Task 2 (T2): [Task Title]

### Scenario
> You want to quickly find all coursework-related tasks in a long task list.


### Instructions (read to participant)
> “Filter the list so that it only shows tasks containing the word ‘COMP’.”


### Success Criteria
- [ ] Filter field contains “COMP”
- [ ] Only matching tasks remain visible
- [ ] Filter result is apparent visually and/or via announcement


### Expected Duration
**Target**: < [15] seconds

### Known Risks
- Auto-filter behaviour may not be obvious
- No-JS mode requires full reload, may confuse participants

---

## Task 3 (T3): [Task Title]

### Scenario
> You notice a typo in a task and want to correct it so your list stays accurate.


### Instructions (read to participant)
> “Edit the task you added earlier and change its title to ‘COMP2850 final lab report’.”

### Success Criteria
- [ ] Edit form opens successfully
- [ ] Title updates to the new value
- [ ] Updated task appears in the list

### Expected Duration
**Target**: < [20] seconds

### Known Risks
- Edit icon may not be clearly identifiable
- Inline form may pose challenges for keyboard/SR access

---

## Task 4 (T4): [Task Title]

### Scenario
> You have finished a task and want to remove it to keep your list tidy.

### Instructions (read to participant)
> “Delete the task titled ‘COMP2850 final lab report’.”

### Success Criteria
- [ ] Confirmation step appears (HTMX JS mode)
- [ ] Task disappears from list
- [ ] Visual cue confirms success

### Expected Duration
**Target**: < [10] seconds

### Known Risks
- Delete action may occur instantly with no warning
- Button may be hard to focus via keyboard

---

## Task Order Rationale

| Task | Complexity | Rationale |
|------|-----------|-----------|
| **T1** | Low | Warm-up task, builds confidence |
| **T2** | Medium | Tests search/filter interaction |
| **T3** | Medium-High | Tests inline editing (more complex interaction) |
| **T4** | Low-Medium | Tests destructive action (confirmation flow) |

---

## Accessibility Considerations

### Keyboard-Only Pilot
- All tasks must be completable via Tab, Enter, Spacebar, Escape
- Focus must be visible at all times
- Tab order must be logical
- No keyboard traps

### Screen Reader Pilot
- All interactive elements must have accessible names
- Status messages must be announced (`aria-live`, `role="status"`)
- Errors must be associated with fields (`aria-describedby`)
- Dynamic content changes must be announced

### No-JS Pilot
- All tasks must work without JavaScript (full page refreshes)
- Form submission must use standard POST/Redirect/GET
- Validation errors must be displayed after redirect
- Navigation must use standard links

---

## Metrics Collected Per Task

| Metric | Unit | How Measured |
|--------|------|--------------|
| **Time-on-task** | Seconds | Stopwatch (start = first action, end = success or abandon) |
| **Completion rate** | % | success / total attempts |
| **Error rate** | Count | Validation errors, incorrect submissions, wrong buttons clicked |
| **Confidence** | 1-5 scale | Post-task self-rating by participant |
| **Abandonment** | Boolean | Did participant give up before completing? |

---

## Expected Results

### Quantitative Targets
- **Completion rate**: ≥ 80% (4 of 5 pilots complete each task)
- **Median time-on-task**: T1 < 10s, T2 < 15s, T3 < 20s, T4 < 10s
- **Error rate**: < 20% (max 1 error per 5 attempts)
- **Confidence**: ≥ 4/5 average across all tasks

### Qualitative Indicators (Success)
- Participants complete tasks without asking for help
- No expressions of frustration or confusion
- Keyboard/SR pilots report no accessibility barriers
- No-JS pilot completes all tasks (though possibly slower)

### Qualitative Indicators (Issues Found)
- Participants hesitate before acting (affordance unclear)
- Participants attempt wrong action (mental model mismatch)
- Errors go unnoticed (validation feedback insufficient)
- Keyboard traps or focus lost (accessibility violation)

---

## Task Refinement Notes

**If pilot reveals issues**:
- Document problems in `05-findings.md`
- Add to backlog (`backlog/backlog.csv`) with severity + inclusion risk
- Prioritize for Week 10 redesign (Task 2)

**If tasks are too easy/hard**:
- Note in findings: "Tasks may need adjustment for difficulty"
- Consider revised tasks for re-pilots (Week 10)

---

**Next**: Use these tasks during pilot sessions (see `02-protocol.md` for session structure).
