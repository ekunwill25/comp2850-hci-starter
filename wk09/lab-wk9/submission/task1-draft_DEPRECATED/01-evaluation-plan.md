# Task 1: Evaluation Plan

**Student ID**: 201843237
**Date**: [10/12/2025]
**Module**: COMP2850 HCI

---

## 1. Evaluation Objectives

**What are you evaluating?**
- A task-management interface where users add, edit and delete simple text based tasks.
- Core usability interactions including feedback messages, inline editing, and keyboard accessibility.

**Why evaluate now?**
- To identify usability issues before submission and prioritise improvements based on user behaviour.
- Design decisions about accessibility, messaging, and interaction flow depend on pilot feedback.

---

## 2. Link to Needs-Finding (LO2)

**Connection to Week 6 job stories**:
- Job Story: When I manage tasks quickly, I need fast interactions, so I don’t waste time.
- Job Story: When updating tasks, I want simple editing, so I avoid confusion or errors.

**How needs-finding shaped evaluation**:
- Needs showed speed and simplicity matter most, so we tested fast CRUD interactions directly.
- Filtering and batch management were lower priority, so excluded from this round.

**Evidence trail**: Week 6 job stories → backlog items → features implemented → evaluation tasks

---

## 3. Evaluation Method Selection

**Method chosen**: Task-based usability testing

**Rationale**:
- Realistic tasks best reveal interaction issues, where interviews by themselves, wouldn’t expose timing problems or barriers.
- Expert evaluation was considered but rejected because peer observation provides more authentic usability evidence.

**Target participants**: Peer students (n=5 minimum)

---

## 4. Success Criteria

**Quantitative metrics**:
- **Time-on-task**: Target < <10s add, <12s edit, <8s complete, <8s delete.
- **Completion rate**: Target ≥ 80% (participants complete without critical errors)
- **Error rate**: Target < 20% (validation errors, incorrect submissions)
- **Confidence**: Target ≥ 4/5 average (post-task self-rating)

**Qualitative indicators**:
- Participants can complete tasks without asking for help
- No confusion about interface affordances (buttons, links, form fields)
- Errors are recoverable without frustration
- No accessibility barriers (keyboard traps, missing announcements, invisible focus)

---

## 5. Evaluation Scope

**In scope**:
- Adding tasks with minimal form validation.
- Editing task titles inline.
- Mark complete and uncomplete interactions.
- Deleting tasks with confirmation feedback.

**Out of scope** (not evaluated in this round):
- User accounts, authentication, or syncing across devices.
- Advanced filtering, tagging, or bulk actions.

---

## 6. Risks & Mitigations

| Risk | Impact | Mitigation |
|------|--------|------------|
| Insufficient participants (n<4) | Can't identify patterns | Recruit early, offer flexible scheduling |
| Technical failures during pilots | Lost data, incomplete sessions | Test setup before pilots, have backup device |
| Pilot bias (peers know the system) | Overly positive results | Use structured tasks, observe actions not opinions |
| Privacy breach (PII in logs) | GDPR violation | Use anonymous session IDs, no names/emails in data |

---

## 7. Ethical Considerations

**Consent**: ✅ Participants will read and confirm consent protocol before starting
**Right to withdraw**: ✅ Participants can stop at any time without penalty
**Anonymity**: ✅ Only session IDs recorded, no names/emails in data files
**Data retention**: ✅ Data stored locally, deleted after analysis (6 weeks max)
**PII in screenshots**: ✅ All screenshots cropped/scrubbed before submission

---

## 8. Timeline

| Activity | Date/Time | Duration |
|----------|-----------|----------|
| Finalize protocol & tasks | Week 9 Lab 1 | 1 hour |
| Recruit participants | Week 9 (before Lab 2) | 2 days |
| Conduct pilots | Week 9 Lab 2 | 2 hours |
| Debrief & synthesise findings | Week 9 Lab 2 | 30 mins |
| Assemble Task 1 pack | Week 9 Lab 2 (end) | 20 mins |
| Submit to Gradescope | Week 9 Lab 2 deadline | - |

---

## 9. Expected Outcomes

**What will you learn from this evaluation?**
- Whether users understand core interactions like add, edit, complete, and delete without hesitation or confusion.
- Whether feedback messages are visible, meaningful, and correctly communicated to users across different input modes.
- Which accessibility barriers still exist, especially for keyboard-only and screen-reader participants.
- Which tasks cause slow performance or repeated errors, helping quantify bottlenecks that need redesign.

**How will results inform redesign?**
- Findings will be prioritised using (Impact + Inclusion) – Effort matrix
- Top 3 issues will be addressed in Week 10 redesign (Task 2)
- Evidence chains will link raw data → findings → backlog items → fixes

---

## References

- [Nielsen: Usability Testing 101](https://www.nngroup.com/articles/usability-testing-101/)
- [W3C: Measuring Accessibility](https://www.w3.org/WAI/test-evaluate/metrics/)
- Week 9 Lab 1 materials: `wk09/lab-wk9/research/`
