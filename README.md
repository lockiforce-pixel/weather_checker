# Weather Checker ☕️

[![Java](https://img.shields.io/badge/Java-17+-red.svg)](https://java.com)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

> Просто ахуенный погодный чекер на Java, потому что PHP — для лузеров

## Features
- ✅ Берет данные с API (не как эти JS-ники с их jQuery)
- ✅ Кеширует в JSON (потому что Redis — для слабаков)
- ✅ Выводит в консоль (веб-морды — это для питонщиков)

## Installation
```bash
git clone https://github.com/yourname/weather-checker.git
cd weather-checker
mvn clean compile exec:java -Dexec.mainClass="com.yourname.WeatherApp"