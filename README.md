# nam

A Clojure library designed to execute nam programs.

## Usage

Library provides function `nam` and macro `nam-macro` which allows writing and executing nam programs. The main
difference
between them is that `nam-macro` enables the use of symbols instead of string with double quotes. Also, there
is `nam-start`
that provide some "low-level" access to work with nam.

Some examples:

```clojure
(nam "12312"
     "124" ==> "5"
     "123" --> "124")

=> "512"
```

```clojure
(nam-macro abcab
           abd ==> 5
           abc --> abd)

=> "5ab"
```

```clojure
(nam.word/word-to-string
  (nam.nam-core/nam-start
    (nam.word/create-word "12312")
    (nam.nam-core/create-nam-program
      [==> "124" "5"]
      [--> "123" "124"])))

=> "512"
```


