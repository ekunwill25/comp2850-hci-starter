# Job Stories — Week 6 Needs-Finding

## Story S1: Filter Persistence
**Situation**: When I'm filtering tasks by project tag (e.g., "COMP2850")
**Motivation**: I want the filter selection to persist across page reloads
**Outcome**: So I can pick up where I left off without re-selecting the filter
**Underlying need**: Because re-filtering 10+ times per session creates cognitive overload and wastes time
**Evidence**: Participant A (notes L5)
**Inclusion risk**: Cognitive, memory impairment, ADHD
**Type**: Job story (situation-specific)

---

## Story S2: Confirmation Feedback
**Situation**: When I submit a form (add task, edit task, delete task)
**Motivation**: I want immediate, explicit confirmation that the action succeeded
**Outcome**: So I can trust the interface without refreshing to verify
**Underlying need**: Because uncertainty about save status causes anxiety and inefficient workarounds (page reload)
**Evidence**: Participant A (notes L12)
**Inclusion risk**: Cognitive, screen reader (if confirmation not announced), low digital literacy
**Type**: Job story

---

## Story S3: Cross-Device Sync
**Situation**: When I switch between mobile and desktop devices
**Motivation**: I want my changes to sync instantly across all devices
**Outcome**: So I don't lose work or have to duplicate effort by retyping tasks
**Underlying need**: Because sync failures create data loss, frustration, and inefficient re-entry workflows
**Evidence**: Participant B (notes L3)
**Inclusion risk**: Cognitive load (remembering what was lost), users with limited time/energy
**Type**: Job story

---

## Story S4: Larger Touch Targets
**Situation**: When I'm using a mobile device or have motor control difficulties
**Motivation**: I want interactive elements (checkboxes, buttons) to be adequately sized
**Outcome**: So I don't accidentally trigger the wrong action
**Underlying need**: Because small touch targets cause mis-clicks, accidental deletions, and frustration for users with shaky hands or using touch screens
**Evidence**: Participant B (notes L8)
**Inclusion risk**: Motor impairment, tremors, mobile/touch users, older adults
**Type**: Job story
**WCAG**: 2.5.5 Target Size (AAA) — minimum 44×44 CSS pixels

---

## Story S5: Screen Reader Button Labels
**Situation**: When I'm navigating with a screen reader
**Motivation**: I want all buttons to have descriptive labels that explain their function
**Outcome**: So I know what each button does before activating it
**Underlying need**: Because generic announcements like "button, button, button" force trial-and-error navigation and waste time
**Evidence**: Participant C (notes L5)
**Inclusion risk**: Blind, screen reader users, cognitive (descriptive labels)
**Type**: Job story
**WCAG**: 4.1.2 Name, Role, Value (A), 2.4.6 Headings and Labels (AA)

---

## Story S6: Keyboard Modal Escape
**Situation**: When a modal dialog opens and I'm using keyboard-only navigation
**Motivation**: I want to close the modal using the Escape key
**Outcome**: So I don't get trapped in the dialog with no way to exit
**Underlying need**: Because keyboard traps prevent users from continuing their workflow and create complete exclusion
**Evidence**: Participant C (notes L20)
**Inclusion risk**: Keyboard-only users, screen reader users, motor impairment
**Type**: Job story
**WCAG**: 2.1.2 No Keyboard Trap (A)

---

## Story S7: Zoom-Responsive Layout
**Situation**: When I zoom to 200% for readability
**Motivation**: I want the layout to reflow so all content remains accessible without horizontal scrolling
**Outcome**: So I can read comfortably without constant zoom adjustments or missing hidden content
**Underlying need**: Because layout breakage at high zoom creates exhausting workflows and excludes low-vision users
**Evidence**: Participant D (notes L5)
**Inclusion risk**: Low vision, older adults, anyone zooming for readability
**Type**: Job story
**WCAG**: 1.4.4 Resize Text (AA), 1.4.10 Reflow (AA)

---

## Story S8: Drag-and-Drop Alternatives
**Situation**: When I need to reorder tasks in my list
**Motivation**: I want button-based controls (move up/down) in addition to drag-and-drop
**Outcome**: So I can reorder tasks without motor fatigue or relying on precise mouse control
**Underlying need**: Because drag-and-drop excludes users with motor impairments, tremors, or who use alternative pointing devices
**Evidence**: Participant D (notes L20)
**Inclusion risk**: Motor impairment, trackball users, keyboard-only users
**Type**: Job story
**WCAG**: 2.1.1 Keyboard (A)