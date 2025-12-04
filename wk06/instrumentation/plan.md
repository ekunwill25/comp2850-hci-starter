# Instrumentation Plan — Week 6

**Purpose**: Capture objective metrics for Week 9 task-based pilots and Week 10 analysis. Focus on validating fixes for Issues #1, #2, #4, #6, and #7 from backlog.

---

## Events to Log

### 1. Task Created
**Trigger**: POST /tasks (success)

**Fields**:
- `ts_iso`: ISO 8601 timestamp (e.g., 2025-01-15T14:23:45Z)
- `session_id`: Anonymous 6-char hex (e.g., `P1_a3f7`)
- `request_id`: Unique per request (for tracing)
- `task_code`: `T1_add` (pilot task identifier)
- `step`: `submit`
- `outcome`: `success` | `validation_error`
- `ms`: Time from request start to response (server-side)
- `http_status`: 200 (success) | 400 (validation error)
- `js_mode`: `js-on` | `js-off`
- `confirmation_shown`: `true` | `false` (whether user saw success feedback)

**Why**: Measure task completion time, compare HTMX vs. no-JS. Validates Issue #2 (No confirmation after form submission).

---

### 2. Validation Error
**Trigger**: POST /tasks (blank title or invalid input)

**Fields**: Same as Task Created, but `outcome=validation_error`, `http_status=400`
- Additional field: `error_persisted`: `true` | `false` (whether error message survived page reload in no-JS mode)

**Why**: Count errors as usability metric; high error rate = poor UX. Validates Issue #2 (error message persistence in no-JS path).

---

### 3. Task Deleted
**Trigger**: POST /tasks/{id}/delete (success)

**Fields**: Same structure as Task Created, but `task_code=T2_delete`
- Additional field: `input_method`: `mouse` | `touch` | `keyboard`
- Additional field: `misclick_before_success`: `0` | `1` | `2` | `3+` (number of accidental clicks)

**Why**: Measure delete task time; verify live region announcement. Validates Issue #4 (Touch targets too small on mobile).

---

### 4. Filter Applied
**Trigger**: GET /tasks?filter={value} (success)

**Fields**:
- `ts_iso`: ISO 8601 timestamp
- `session_id`: Anonymous 6-char hex
- `request_id`: Unique per request
- `task_code`: `T3_filter` (pilot task identifier)
- `step`: `filter_apply`
- `outcome`: `success`
- `ms`: Time from request start to response
- `http_status`: 200
- `js_mode`: `js-on` | `js-off`
- `filter_value`: The filter tag applied (e.g., `COMP2850`)
- `result_count`: Number of tasks returned after filtering

**Why**: Track filter usage. Validates Issue #1 (Filter selection resets on page reload) - measure baseline re-application rate.

---
### Example Rows
2025-01-15T14:23:45Z,P1_a3f7,req_001,T3_add,submit,success,120,200,js-on
2025-01-15T14:24:10Z,P1_a3f7,req_002,T4_delete,submit,success,85,200,js-on
2025-01-15T14:25:00Z,P2_b8c4,req_003,T3_add,submit,validation_error,0,400,js-off