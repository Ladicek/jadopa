# Jadopa

_Jadopa_ is a standalone parser of Java documentation comments, informally
known as Javadoc. If you use Java 8+, you should probably use the DocTree API,
but if you are stuck with a previous version for some reason, Jadopa can help.

Jadopa is only a parser, it doesn't aim to be a complete Javadoc implementation
and you probably wouldn't be able to write one on top of it. It is
HTML-agnostic, so it will happily consume malformed HTML. In fact, it doesn't
presume that you Javadoc will be HTML; it can easily be Markdown or any other
markup language.

The only thing that Jadopa does is separating text from documentation tags and
providing typed access to all the standard tags.

