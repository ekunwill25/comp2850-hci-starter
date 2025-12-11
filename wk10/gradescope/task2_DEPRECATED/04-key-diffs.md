# Key Diffs — Code and UX Changes

**Purpose**: Document specific code/template/CSS changes made during Week 10 redesign.

---

## Change 1: Validation Error Accessibility (T2 Edit Task)

### Problem
Validation errors were **not announced to screen readers**, causing SR users (P5) to miss feedback and assume the edit succeeded. Keyboard-only users (P2) struggled to locate errors due to lack of focus management.

**Evidence**: Week 9 `05-findings.md` — Finding A1 (“Success/error not announced”), Finding F5 (“Keyboard focus unclear”)

---

### Code Changes

#### Before  
**File**: `templates/tasks/edit.peb`
```html
<label for="title">Title</label>
<input id="title" name="title" value="{{ task.title }}">

{% if error %}
  <span class="error">{{ error }}</span>
{% endif %}
