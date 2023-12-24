# About The Project

During my university studies, I write  a small program as part of the Java course. The program searches all files of a certain type in a directory tree and format all locations of a given token. In order to speed up the search, certain directories are able to be excluded from the search.


## Built with

- Java
- Gradle 


## Installation Usage

1. Clone the repo from GitHub
  ```sh
  https://github.com/anastasiia-syvtsova/Token_finder.git
   ```
2. Open cloned repo in integrated development environment (ex. IntelliJ IDEA, Eclipse, Visual Studio)
3. Start the program via main method. 


## Usage

1. Give root folder, ignore file, file extension and search token.
2. Click enter.
3. You become the result of the search in the following format:

  ```java
[ DIRECTORY ] directory was ignored .

[ FILE ]:[ ROW ],[ COLUMN ]> [ TEXT BEFORE TOKEN IN SAME LINE ]**[ TOKEN ]**[TEXT AFTER TOKEN IN SAME LINE ]
[ FILE ] includes **[ TOKEN ]** [X] times .

The project includes **[ TOKEN ]** [Y] times .
   ```


## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.
