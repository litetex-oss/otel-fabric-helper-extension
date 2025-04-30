[![Latest version](https://img.shields.io/maven-central/v/net.litetex/otel-fabric-helper-extension?logo=apache%20maven)](https://mvnrepository.com/artifact/net.litetex/otel-fabric-helper-extension)
[![Build](https://img.shields.io/github/actions/workflow/status/litetex-oss/otel-fabric-helper-extension/check-build.yml?branch=dev)](https://github.com/litetex-oss/otel-fabric-helper-extension/actions/workflows/check-build.yml?query=branch%3Adev)

# OpenTelemetry Agent Helper Extension for Fabric

This [OpenTelemetry Agent extension](https://opentelemetry.io/docs/zero-code/java/agent/extensions/) helps instrumenting [FabricMC](https://github.com/FabricMC/fabric-loader).

### What does it do?

_This extension was written to make [OpenTelemetry Instrumentation Extension for Fabric](https://github.com/litetex-oss/mcm-otel-instrumentation-extension) work_

* Ensures that KnotClassLoader correctly detects changes on its classpath
    * This ensure that the ``opentelemtry-api`` is correctly injected when a mod is loaded
    * Otherwise only outdated version 1.0/1.4 of the ``opentelemtry-api`` is injected which is incompatible with modern versions of it

## Installation

[Installation guide for the latest release](https://github.com/litetex-oss/otel-fabric-helper-extension/releases/latest#Installation)

### Quick setup
```
java -javaagent:opentelemetry-javaagent.jar \
  -Dotel.javaagent.extensions=otel-fabric-helper-extension.jar
  -jar myapp.jar
```

## Contributing
See the [contributing guide](./CONTRIBUTING.md) for detailed instructions on how to get started with our project.

## Dependencies and Licenses
View the [license of the current project](LICENSE) or the [summary including all dependencies](https://litetex-oss.github.io/otel-fabric-helper-extension/dependencies)

The project is based on [this example](https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main/examples/extension).
