# Spring MVC Project

### Поднять базу в докере

Один раз сделать (запустит базу)
```sh
docker-compose up -d
```

Если делаешь изменения в миграциях, то надо базу пересоздать
```sh
docker-compose down -v
```

```sh
docker-compose up -d
```

Команды выполнять в терминале в папке с проектом