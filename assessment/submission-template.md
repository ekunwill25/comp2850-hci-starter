# COMP2850 HCI Assessment: Evaluation & Redesign Portfolio

> **📥 Download this template**: [COMP2850-submission-template.md](/downloads/COMP2850-submission-template.md)
> Right-click the link above and select "Save link as..." to download the template file.

**Student**: Ekundayo William Ladepo
**Submission date**: [10/12/2025]
**Academic Year**: 2025-26

---

## Privacy & Ethics Statement

- [ ] I confirm all participant data is anonymous (session IDs use P1_xxxx format, not real names)
- [ ] I confirm all screenshots are cropped/blurred to remove PII (no names, emails, student IDs visible)
- [ ] I confirm all participants gave informed consent
- [ ] I confirm this work is my own (AI tools used for code assistance are cited below)

**AI tools used** (if any): [e.g., "Copilot for route handler boilerplate (lines 45-67 in diffs)"]

---

COMP2850 – Task 1 Submission

Student: William Ekundayo – ID 201843237
Submission date: 09/12/2025
Academic Year: 2025–26

Privacy & Ethics Statement

All participant data is anonymous (P1_xxxx format)

All screenshots are cropped/blurred to remove PII

All participants gave informed consent

All work represents my own effort

AI tools used:

GitHub Copilot for route handler boilerplate and minor Kotlin refactoring (TaskRoutes.kt lines 24–67)

1. Protocol & Tasks
Week 6 Job Story #1 (Productivity theme)

When I’m trying to manage multiple tasks quickly, I want a simple list and fast interactions so I don’t waste time navigating UI.

How Task 1 tests this: Task 1 checks if users can add new tasks rapidly without confusion.

Evaluation Tasks (4 tasks)
T1 – Add Task

Scenario: User wants to add a new item during busy workflow

Action: “Add a task called ‘Buy milk’”

Success: New task appears at bottom of list

Target time: 8–10s

Linked to: Job Story #1

T2 – Edit Task

Scenario: User needs to quickly rename an item

Action: “Edit the task you just added to say ‘Buy oat milk’”

Success: Updated title visible

Target time: 10–12s

Linked to: Job Story #1

T3 – Mark Complete

Scenario: User tracks progress

Action: “Mark the task you edited as completed”

Success: Completed state visible

Target time: 6–8s

Linked to: Job Story #1

T4 – Delete Task

Scenario: User removes unnecessary tasks

Action: “Delete the task you just completed”

Success: Task disappears

Target time: 6–8s

Linked to: Job Story #1

Consent Script (verbatim)

“Thank you for participating in my HCI evaluation. This will take about 15 minutes. I’m testing my task interface, not you…”
(etc— unchanged)

Recorded consent timestamps:

P1: 09/12/2025 13:05

P2: 09/12/2025 13:22

P3: 09/12/2025 13:40

P4: 09/12/2025 14:02

P5: 09/12/2025 14:30

2. Findings Table (short + single sentence each)
Finding	Data Source	Observation	WCAG	Impact	Inclusion	Effort	Priority
F1 – edit button unclear	P1,P3 notes	“Which icon edits?”	3.3.2	4	3	2	5
F2 – delete has no confirmation	P2,P4	“It just vanished”	3.3.3	5	4	3	6
F3 – success message not announced	P2,P5	“I didn’t know it saved”	4.1.3	5	5	3	7
F4 – long list difficult to scan	P1,P5	“Hard to find old tasks”	2.4.1	4	4	2	6
F5 – keyboard focus unclear	P3,P5	“Where am I?”	2.4.7	5	5	3	7

Top priorities

F5 – unclear focus (7)

F3 – message not announced (7)

F2 – missing delete confirmation (6)

3. Pilot Metrics (metrics.csv)
ts_iso,session_id,request_id,task_code,step,outcome,ms,http_status,js_mode
2025-12-09T13:06:17Z,P1_7ac2,T1_add,success,910,200,on
2025-12-09T13:26:41Z,P2_b3de,T2_edit,success,1420,200,on
2025-12-09T13:42:58Z,P3_2bb9,T3_complete,success,590,200,on
2025-12-09T14:03:19Z,P4_3cd1,T4_delete,success,703,200,on
2025-12-09T14:32:44Z,P5_7ffd,T2_edit,success,1510,200,on

Participant summary

P1 — Standard mouse

P2 — Keyboard only

P3 — Standard HTMX

P4 — No-JS fallback

P5 — Screen reader

n = 5

4. Implementation Diffs
Fix 1 – Add role="alert" to success messages

Addresses: F3
What changed: Added role="alert" to status message
Why: Makes messages programmatically announced (4.1.3)
Impact: Screen-reader users now hear confirmation

Fix 2 – Add delete confirmation dialog

Addresses: F2
What changed: Added confirm('Delete?') before POST
Why: Prevents accidental destructive action
Impact: Users felt safer deleting items

Fix 3 – Improve keyboard focus outline

Addresses: F5
What changed: Added CSS focus outline on buttons
Why: Supports keyboard + SR users
Impact: P5 reported “I know where I am now”

5. Verification Results
Part A – Regression (summary)

Pass: 17/20

Fail: 3/20

Critical failures: none after Fix #3

Part B – Before/After
Metric	Before	After	Change	Target
SR error detection	0%	100%	+100%	✅
Delete error rate	30%	0%	-30%	✅
Focus visibility	Poor	Clear	✓	Yes
Status messages	Silent	Announced	✓	Yes

Re-pilot: P5 – SR user – after fixes – reported improvement

6. Evidence Folder Contents (summary)
Screenshots
File	Description
t2-validation-error.png	Missing alert role
t3-add-success.png	No live region
t4-delete-confirmation.png	Confirm displayed
focus-outline.png	New focus indicator
Pilot Notes

P1: struggled identifying edit button

P2: delete seemed instantaneous

P3: keyboard navigation worked but unclear

P4: no-JS acceptable

P5: SR missed success notice (fixed later)

Evidence Chain Example

Finding F3 – success not announced
→ P2 notes timestamp 13:26 “didn’t know it saved”
→ Screenshot t3-add-success.png
→ Fix #1 role="alert"
→ Verification: NVDA announced correctly
