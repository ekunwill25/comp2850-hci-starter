## Slide 1 — Overview

- Built a task manager with server-first Kotlin Ktor backend

- HTMX-enhanced interactions + full no-JS fallback

- Aim: WCAG 2.2 AA compatibility (focus, announcements, keyboard)

- Core features: Add / Edit / Complete / Delete tasks

## Slide 2 — Needs-Finding (Week 6)

- Conducted 5 peer interviews → 5 job stories

- Most common need: “I want clear confirmation when actions succeed.”

- Influenced Task 1–4 evaluation design

- Result: Prioritised feedback and affordance clarity

## Slide 3 — Implementation Journey (Weeks 7–8)

- Week 7: Inline editing, form validation, accessibility audit

- Week 8: Filtering, refactored partials, no-JS parity work

- Challenge: Keeping behaviour identical between HTMX and no-JS paths

## Slide 4 — Evaluation (Week 9)

- Ran 5 pilots across: HTMX, keyboard-only, no-JS, screen reader

- 100% task completion overall

- Found: SR users unaware of success messages & unclear keyboard focus

- Metrics: Median time 0.6–1.4s, no validation errors except SR unnoticed one

## Slide 5 — Redesign (Week 10)

- Added role="alert" to announcements

- Added focus outline for keyboard accessibility

- Began implementing delete confirmation

- Fixed slow/no-feedback behaviour for no-JS edit

## Slide 6 — Reflection

- Learned importance of inclusive design + dual paths

- Want to improve delete UX + focus management further

- Ask peers: “How do you handle feedback without JS?”