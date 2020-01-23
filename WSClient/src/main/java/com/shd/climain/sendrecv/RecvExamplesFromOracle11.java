package com.shd.climain.sendrecv;

public class RecvExamplesFromOracle11 {

}
/* Here is an example of a listener that requests invocations, one at a
* time, until a complete message has been accumulated, then processes
* the result, and completes the {@code CompletionStage}:
* <pre>{@code     WebSocket.Listener listener = new WebSocket.Listener() {
*
*        List<CharSequence> parts = new ArrayList<>();
*        CompletableFuture<?> accumulatedMessage = new CompletableFuture<>();
*
*        public CompletionStage<?> onText(WebSocket webSocket,
*                                         CharSequence message,
*                                         boolean last) {
*            parts.add(message);
*            webSocket.request(1);
*            if (last) {
*                processWholeText(parts);
*                parts = new ArrayList<>();
*                accumulatedMessage.complete(null);
*                CompletionStage<?> cf = accumulatedMessage;
*                accumulatedMessage = new CompletableFuture<>();
*                return cf;
*            }
*            return accumulatedMessage;
*        }
*    ...
*    } } </pre>
*
* @since 11
*/