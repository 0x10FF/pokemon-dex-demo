# Purpose
This is a sample application for demonstration/test purposes.

https://github.com/0x10FF/pokemon-dex-demo/assets/570039/fc7edcf0-d11c-40cb-8b53-9edf95500bda

# Features of this code base
| Item                     | Purpose                                                       |
| -------------------------| ------------------------------------------------------------- |
| [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)-style, clean architecture, [SOLID](https://en.wikipedia.org/wiki/SOLID) foundation  | Given time, best effort to deploy these principles. There are also opportunities to further extract few interfaces (Liskov principle) to improve code quality.                                                        |
| Caching                  | Android Jetpack caching layer with Paging library, Room DB. Sample PagingMeditor, along with Room generated paging source                                     |
| Navigation               | Android Jetpack navigation layer                              |

# Work in progress features
| Item                     | Purpose                                                       |
| -------------------------| ------------------------------------------------------------- |
| Testing                  | At the time of this writing, it is challenging to use Dagger/Hilt infrastructure to setup testing scaffolding with View based architecture, however once setup up - following tests will be much easier to setup                                             |


# Roadmap
| Item                          | Purpose                                                  |
| ------------------------------| -------------------------------------------------------- |
| Improve testability           | Increase coverage in unit testing layer                  |
| Migrate to Compose            | Kotlin based declarative UI - not only for Android platform but also easier maintenance and capabilities of UI layer.                                  |
| Feature: Add a "capture cart" | Author admits he his knowledge of Pokemon capture semantics is lacking however my nephew is an expert                                                     |
