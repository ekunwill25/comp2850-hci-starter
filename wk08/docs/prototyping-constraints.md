# Prototyping Constraints & Trade-offs

## Rendering splits
- Full page: `/tasks` returns layout + list + pager.
- Fragment: `/tasks/fragment` returns list + pager + OOB status.

## URL & History
- `hx-push-url="true"` on filter and pager links keeps Back/Forward meaningful.

## Accessibility hooks
- Live region `#status` announces changes.
- Result count associated with list via `aria-describedby`.

## Performance notes
- Page size: 10 items; consider server time vs client cost.
- Fragments avoid re-sending the full layout.

## Future risks
- Template duplication between full page and fragments.
- Focus management after deletes (ensure next focusable target).

## Accessibility Verification:
- 0. Baseline Check - Verified, it is complete
- 1.1 - Complete
- 1.2 - Complete
- 1.3 - Complete
- 1.4 - Complete
- 1.5.1 - Complete, file created
- 1.5.2 - Complete, file created
- 1.5.3 - Complete, file created
- 1.5.4 - Complete, file created
- 1.5.5 - Complete, routing updated
- 2.1 - Class added
- 2.2 - pager file created
- 2.3 - filter form added
- 2.4 - GET handlers added
- 3 - Test completed
- 4 - Document trade off complete

