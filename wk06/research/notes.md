# Interview Notes — Week 6

## Participant A
**Date**: [2025-11-29 11:32]
**Context**: Uses keyboard only, prefers dark mode
**Consent**: ✅ Confirmed verbally
**Duration**: 5 minutes

### Q1: Last time you used a task manager
**Response**: "I use Notion for uni work. And because it clashes with the rest of my notes, it makes finding my uni work stressful and tiring.

**Observations**:
- Time pressure context (deadline stress)
- Tag-based workflow (not just chronological)

**Themes**: `cognitive_load`, `deadline_anxiety`

---

### Q2: What frustrates you?
**Response**: "After submitting a form, I have no idea if I submitted it, meaning I have to refresh the page to see if I have."

**Observations**:
- Lack of confirmation feedback
- Low trust in interface
- Workaround = page reload (inefficient)

**Themes**: `status_feedback`, `confirmation`, `trust`

---

### Q3: Lost track of important task?
**Response**: "I forgot to submit an assignment, and I only found out that I had not submitted it whilst I was submitting the next one. There should be a way or notifier that lets me know that I have not submitted it."

**Observations**:
- List length issue (visibility)
- Prioritisation need
- Consequence = missed deadline (high impact)

**Themes**: `prioritisation`, `visibility`, `urgent_tasks`

---

### Q4: Work without a mouse?
**Response**: "My trackpad broke last month. I tried using Tab to navigate, but some buttons were impossible to reach. Had to borrow a friend's mouse."

**Observations**:
- Keyboard-only experience = friction
- Temporary impairment (broken hardware)
- Exclusion from features

**Themes**: `keyboard_nav`, `temporary_impairment`, `button_accessibility`

---

### Q5: Eyes closed / bright sunlight?
**Response**: "Haven't tried eyes closed, but in sunlight I can't read low-contrast text. I increase zoom but then the layout breaks."

**Observations**:
- Contrast issue (situational disability)
- Zoom breaks responsive design
- Environmental factor (sunlight)

**Themes**: `contrast`, `zoom`, `responsive_design`

---

### Q6: One feature to add?
**Response**: "A way to see progress—like, 'You've completed 8 out of 12 tasks this week.' That would motivate me."

**Observations**:
- Motivation through feedback
- Progress visualisation
- Weekly scope (not just daily)

**Themes**: `progress_tracking`, `motivation`, `feedback`

---

## Summary (Participant A)
**Top pain points**:
1. No confirmation feedback → uncertainty
2. Keyboard navigation gaps → temporary exclusion
3. Contrast issues in bright light → situational disability

**Job story ideas**:
- "When I'm filtering tasks, I want the selection to persist across page reloads so I don't lose my place."
- "When I submit a form, I want immediate confirmation so I know it worked without refreshing."
- "When my mouse breaks, I want full keyboard access so I can still complete tasks."

## Participant B
**Date**: [2025-11-29 13:40]
**Context**: Mobile-first, multitasks frequently between devices (mobile + laptop)
**Consent**: ✅ Confirmed via email
**Duration**: 6 minutes

### Q1: Last time you used a task manager
**Response**: "I switch between my phone and laptop constantly. Started a shopping list on my phone during lunch, but when I got home and opened my laptop, nothing synced. Had to retype everything."

**Observations**:
- Cross-device workflow expectation
- Real-time sync failure = friction
- Mobile context -> Desktop context transition

**Themes**: `sync_issues`, `cross_device`, `data_loss` 

---

### Q2: What frustrates you?
**Response**: "The buttons are way too small on mobile. I keep accidentally tapping 'Delete' when I mean to tap 'Edit'. There's no undo either, so I've lost tasks permanently."

**Observations**:
- Touch target size inadequate
- Destructive action without confirmation
- No recovery mechanism

**Themes**: `touch_targets`, `accidental_deletion`, `undo_feature`

---

### Q3: Lost track of important task?
**Response**: "I had a doctor's appointment reminder buried in a list of 5+ tasks. Didn't see it until two hours after I'd missed it. Everything just looks the same—no way to make something stand out."

**Observations**:
- Visual hierarchy problem
- Time-sensitive tasks are not surfaced
- List overload (when above 5 items)

**Themes**: `visual_hierarchy`, `time_sensitive`, `notification_failure`

---

### Q4: Work without a mouse?
**Response**:  "I mostly use my phone anyway, but when I tried voice input once, it kept autocorrecting 'Buy milk' as 'By milk'. Gave up after a while."

**Observations**:
- Voice input accuracy issues
- Alternative input method frustration
- Mobile-native user perspective

**Themes**: `voice_input`, `speec_recognition`, `alternative_input`

---

### Q5: Eyes closed / bright sunlight?
**Response**:  "I use my phone outdoors all the time. The screen is basically unreadable in direct sunlight, even at full brightness. I have to cup my hand over it like a cave."

**Observations**:
- Environmental accessibility barrier
- Brightness alone insufficient
- Physical workaround (hand shadow)

**Themes**: `outdoor_visibility`, `contrast_ratio`, `environmental_factors`

---

### Q6: One feature to add?
**Response**:  "Smart suggestions based on what I usually do. Like, every Monday I have a team meeting—just auto-create that task for me instead of making me type it every week."

**Observations**:
- Desire for automation
- Pattern recognition opportunity
- Recurring task frustration

**Themes**: `automation`, `recurring_tasks`, `smart_suggestions`

---

## Summary (Participant B)
**Top pain points**:
1. Sync failures across devices → data loss and duplication
2. Small touch targets → accidental destructive actions
3. No visual hierarchy → missed time-sensitive tasks

**Job story ideas**:
- "When I switch from mobile to desktop, I want my changes to sync instantly so I don't lose work or duplicate effort."
- "When I'm about to delete a task, I want a confirmation dialog so I don't accidentally remove important items."
- "When I have urgent tasks, I want them visually distinct from regular tasks so I don't miss deadlines."

## Participant C
**Date**: [2025-12-01 09:40]
**Context**: Screen reader user, keyboard-only navigation
**Consent**: ✅ Confirmed verbally
**Duration**: 8 minutes

### Q1: Last time you used a task manager
**Response**: "I tried a new app last week. The screen reader kept announcing 'button, button, button' without saying what the buttons did. Took me 10 minutes to figure out which one marked tasks as complete."

**Observations**:
- Missing ARIA labels
- Navigation frustration multiplied
- Time cost = 10 minutes for simple action

**Themes**: `screen_reader`, `aria_labels`, `button_semantics` 

---

### Q2: What frustrates you?
**Response**: "When I press Enter on a task, nothing happens. I have to Tab through five more elements to find the actual 'Edit' button. Why isn't the task itself clickable?""

**Observations**:
- Keyboard interactin expectation mismatch
- Unnecessary navigation steps
- Logical grouping issue

**Themes**: `keyboard_shortcuts`, `focus_management`, `interaction_patterns`

---

### Q3: Lost track of important task?
**Response**:  "I can't see colour-coded priorities, obviously. The app uses red for urgent tasks, but my screen reader just says 'Task: Buy groceries, List item.' No mention of urgency at all."

**Observations**:
- Color as sole information carrier
- Screen reader missing semantic priority info
- Accessibility annotation gap

**Themes**: `colour_dependency`, `semantic_html`, `priority_announcement`

---

### Q4: Work without a mouse?
**Response**: "I never use a mouse. But most apps have mystery buttons I can't reach with Tab. Or I get trapped in a modal dialog with no way to close it using the keyboard."

**Observations**:
- Keyboard trap issue (modal dialogs)
- Tab order incomplete or illogical
- Permanent dependency on keyboard

**Themes**: `focus_trap`, `modal_accessibility`, `keyboard_only`

---

### Q5: Eyes closed / bright sunlight?
**Response**: "I'm slightly colour-blind, so that's my default experience. But I'll say this—when developers use proper headings and landmarks, I can navigate in seconds. When they don't, it's like wandering through an unmarked warehouse."

**Observations**:
- Structural navigation critical
- Heading hierarchy = wayfinding
- Metaphor reveals mental model ("unmarked warehouse")

**Themes**: `document_structure`, `landmarks`, `navigation_efficiency`

---

### Q6: One feature to add?
**Response**: "Keyboard shortcuts for everything. And I mean everything—creating tasks, filtering, marking complete. If I can do it with a mouse, I should be able to do it with a keyboard."

**Observations**:
- Feature parity expectation
- Efficiency through shortcuts
- Frustration with mouse-dependent features

**Themes**: `keyboard_shortcuts`, `feature_parity`, `efficiency`

---

## Summary (Participant C)
**Top pain points**:
1. Missing ARIA labels → screen reader announces generic text
2. Keyboard traps in modals → cannot escape or close dialogss
3. Color-only priority → no semantic information for urgency

**Job story ideas**:
- "When I navigate with a screen reader, I want buttons labeled with their function so I know what they do before activating them."
- "When a modal opens, I want to close it with Escape key so I don't get trapped in the dialog."
- "When tasks have priorities, I want semantic markup so my screen reader announces urgency, not just color."

## Participant D
**Date**: [2025-12-02 14:20]
**Context**: Uses browser zoom (200%), motor control difficulties
**Consent**: ✅ Confirmed via voice call
**Duration**: 7 minutes

### Q1: Last time you used a task manager
**Response**: "I zoomed in to 200% because I can't read small text. Half the interface disappeared off the screen. Had to zoom out, click something, then zoom back in to read it. Exhausting."

**Observations**:
- Zoom breaks layout responsiveness
- Horizontal scrolling issue likely
- Workflow = constant zoom adjustment

**Themes**: `zoom_responsiveness`, `layout_breakage`, `cognitive_fatigue` 

---

### Q2: What frustrates you?
**Response**: "Checkboxes are so tiny. I have shaky hands, so I keep missing them and accidentally clicking the task text instead, which opens an edit dialog I didn't want."

**Observations**:
- Motor control difficulty
- Checkbox size inadequate
- Unintended action triggers (edit dialog)

**Themes**: `motor_control`, `checkbox_size`, `accidental_clicks`

---

### Q3: Lost track of important task?
**Response**: "I set a task to repeat weekly, but there's no visual indicator showing it's recurring. So I keep thinking I forgot to add next week's task, and I end up with duplicates."

**Observations**:
- Recurring task indicator missing
- Confusion leads to duplication
- Visual distinction need

**Themes**: `recurring_indicators`, `visual_feedback`, `task_duplication`

---

### Q4: Work without a mouse?
**Response**: "I use a trackball because regular mice hurt my wrist. But dragging tasks to reorder them is impossible for me—my hand cramps up. I wish there was a button to move tasks up or down instead."

**Observations**:
- Drag-and-drop excludes some motor impairments
- Trackball user (alternative pointing device)
- Button-based alternative needed

**Themes**: `drag_drop_alternative`, `motor_fatigue`, `reordering_methods`

---

### Q5: Eyes closed / bright sunlight?
**Response**: "I already zoom to 200%, so text gets blurry when stretched. And some buttons have icons with no text labels, so at high zoom I can't tell what they are, just pixelated blobs."

**Observations**:
- Icon-only buttons fail at high zoom
- Text rendering quality degrades
- Visual recognition breakdown

**Themes**: `icon_clarity`, `text_labels`, `high_zoom_design`

---

### Q6: One feature to add?
**Response**: "Bigger click targets everywhere. And maybe a setting to increase spacing between elements so I'm not constantly misclicking."

**Observations**:
- Spatial density issue
- Customisation preference
- Motor precision accommodation

**Themes**: `click_target_size`, `spacing_customisation`, `motor_accessibility`

---

## Summary (Participant D)
**Top pain points**:
1. Zoom breaks layout → content hidden, horizontal scroll required
2. Small checkboxes → motor control challenges, mis-clicks
3. Drag-and-drop only → excludes users with motor fatigue

**Job story ideas**:
- When I zoom to 200%, I want the layout to reflow so I can access all features without horizontal scrolling."
- "When I need to reorder tasks, I want keyboard or button-based controls so I don't rely solely on drag-and-drop."
- "When I interact with checkboxes, I want larger touch targets so my shaky hands can click accurately."