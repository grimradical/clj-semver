# `clj-semver`

Functions for parsing, comparison, and manipulation of
[semantic version strings](http://semver.org). The intent is to
implement the actual spec, including proper comparisons on pre-release
and build fields.

## Usage

The main namespace for date-time operations in the `clj-semver`
library is `clj-semver.core`.

    user> (use 'clj-semver.core)

Parse a version string:

    user> (version "1.0.0")
    {:major 1, :minor 0, :patch 0, :pre-release nil, :build nil}

    user> (version "1.0.0-alpha.1")
    {:major 1, :minor 0, :patch 0, :pre-release "alpha.1", :build nil}

    user> (version "1.0.0-alpha.1+build.234")
    {:major 1, :minor 0, :patch 0, :pre-release "alpha.1", :build "build.234"}

They are regular clojure maps, so you can interrogate them the
expected way:

    user> (:minor (version "1.2.3"))

The `version` function is idempotent, so you can call it on a
pre-parsed version and it'll will just pass-through the value. This
makes it easier to create functions that operate on either string
versions, or parsed version maps.

Comparison is straightforward:

    user> (newer? "1.2.0" "1.0.1")
    true
    user> (older? "1.0.0-alpha.1" "1.0.0")
    true

## ...etc

Apache licensed
