# Simple `geometry` tests demo

В этом проекте демонстрируется работоспособность `java-testlib` на упрощенной задаче из контеста про геометрию.

## Описание тестируемой задачи
Задача состоит в том, чтобы написать два класса: `Point` и `Square`. Необходимые методы классов можно посмотреть в файлах проекта (Например, `reference/Square.java`).

## Сборка и запуск проекта:
+ Соберите `java-testlib`, используя скрипт `build-testlib.sh` в репозитории `java-testlib`.
+ Подключите `testlib.java` как внешнюю библиотеку.
+ В конфигурации запуска/сборки проекта, добавьте `Before launch task`, которая будет запускать скрипт `make-jars.sh`. Это позволит пересобирать `jar`-архивы с решениеями для тестирования и проверки перед каждом запуском программы.
+ Готово, можно запускать проект нажав на кнопку `run`.


## Copyright

![Creative Commons Licence](https://i.creativecommons.org/l/by-sa/4.0/88x31.png)

All materials are available under license [Creative Commons «Attribution-ShareAlike» 4.0](http://creativecommons.org/licenses/by-sa/4.0/).\
When borrowing any materials from this repository, you must leave a link to it, also, you should include my name: **Konstantin Leladze**.

__© Konstantin Leladze__
