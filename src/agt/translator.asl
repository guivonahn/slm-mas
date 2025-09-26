!getTranslation("hello, this is a test!").

+!getTranslation(X)
    <- translate(X, Y);
    +translation(X, Y);
    .print(Y).

{ include("$jacamo/templates/common-cartago.asl") }
{ include("$jacamo/templates/common-moise.asl") }