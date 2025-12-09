1. Dual-path complexity:
Maintaining HTMX was more complicated than I thought. I had to keep telling myself that every feature needed a fallback version. What really helped was treating HTMX responses as enhancements instead of a separate feature, so I always built the full page first and then added partial responses.

2. Error handling:
Making error messages accessible forced me to think about ARIA and visible status text. I realised WCAG requires feedback that isn’t only visual, so errors needed semantic HTML and persistent text, not just CSS styling or pop-ups. I discovered new requirements around “programmatically determinable” error states.

3. Trade-offs:
The no-JS delete confirmation felt like the biggest trade-off because the fallback experience is less smooth and modern than the HTMX version.

4. Testing:
The scripted no-JS testing was very useful because it revealed assumptions I didn’t realise I was making about client behaviour.

5. Inclusion impact:
The dual-path design benefits users with slow devices, limited connectivity, or locked-down environments where JavaScript may not work reliably.

6. Next steps:
 I would focus on reducing duplicate template logic and improving consistency between full-page and partial views to avoid technical debt.