# nam

A Clojure library designed to execute nam programs.

## Usage

Example:

```clojure
(nam-start
  (create-nam-word "12312")
  (create-nam-program
    [==> "124" "5"]
    [--> "123" "124"]
    )
  )
```

