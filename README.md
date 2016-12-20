# Scala cell mode for IDEA

Enables you to run a "cell" (code surrounded by `///`)

```scala
///
println("foo")
(0 to 10).foreach(println)
///
```

Inspired by matlab's cell mode. Also [pycharm-cellmode](https://github.com/julienr/pycharm-cellmode).

## Installation

1. clone this repository
2. open Intellij and make a new plugin project (
   1. `File > New > IntelliJ Platform Plugin > Scala`
   2. press `Next` and enter ScalaCellMode as `Project name` and `Module name`. Also set the `Content root` at the path of the repo
3. Build (`Build > Prepare Plugin Module ... For Deployment` )
4. Install. Step 3 will result in the creation of an archive(`ScalaCellMode.zip`). 
   1. `File > Settings > Plugins`
   2. press `Install plugin from disk...` and navigate to `ScalaCellMode.zip` 
   3. restart IntelliJ





