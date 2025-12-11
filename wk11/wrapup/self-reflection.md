# Self-Reflection (≈400–600 words)

## What changed and why
- The redesign targeted a key accessibility failure from Week 9: screen reader and keyboard users could not detect validation errors on the edit-task form (T2). Although the interface looked functional visually, critical WCAG 4.1.3 issues meant some participants assumed their edits had saved when they had not. This made T2 the weakest-performing task and a clear barrier to inclusion.
To fix this, I added role="alert", aria-describedby, an error summary with auto-focus for no-JS users, and improved error text contrast. These changes aimed to create a predictable, perceivable error flow across HTMX and no-JS paths.

## Evidence of impact
- Completion rate increased from 80% → 100%.
- Error rate dropped from 33% → ≤10%.
- Median time improved as participants corrected errors more quickly.
- SR accessibility improved from 0% → 100%, with NVDA announcing all errors.

## Inclusion
- The fix benefits screen reader users, keyboard-only users, and low-vision users, while also reducing cognitive load for everyone. No trade-offs were introduced—sighted mouse users experience the same flow, just clearer feedback. Remaining risks include refining multi-error summaries and expanding parity for delete confirmation in the no-JS path.

## Process
- The iterative cycle (pilot → fix → re-test) was effective and ensured changes were grounded in real evidence rather than assumptions. HTMX and server-first templates made updates fast without breaking no-JS behaviour.
For Semester 2, I would start accessibility testing earlier and schedule more diverse peer pilots sooner to catch issues earlier in the build.

## References
- 05-findings.md, 01-redesign-brief.md, 03-before-after-summary.md, 04-key-diffs.md
- Git commits: alert roles, aria links, error-summary focus, contrast fixes

