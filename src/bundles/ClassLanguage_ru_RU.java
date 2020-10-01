        package bundles;

        import java.text.ParseException;
        import java.time.LocalDateTime;
        import java.time.format.DateTimeFormatter;
        import java.util.ListResourceBundle;
        import java.util.Locale;

        public class ClassLanguage_ru_RU extends ListResourceBundle {

            private static final Object[][] contents = {

                    {"lan", "ru"},//*
                    {"country", "RU"},//*


                    //Регистрация/Авторизация
                    {"info_button", "Пароль должен содержать не менее 4 символов \n(допускается использование только английских прописных букв и цифр) "},
                    {"cancel_button", "Отмена"},
                    {"write_userName", "Имя Пользователя"},
                    {"write_userPass", "Пароль"},
                    {"Reg_label", "Придумайте логин и пароль"},

                    //Блок authorization.fxml:
                    {"selectLanguage", "Выбрать язык"},
                    {"registrationLogin", "Нет аккаунта?"},
                    {"loginBut", "Войти"},
                    {"regBut", "Сделать!"},
                    {"Autor", "Авторизация"}, //**

                    //(дополнение)
                    {"DB_available", "База данных доступна"},
                    {"DB_is_not_available", "База даных не доступна"},
                    {"server_is_not_available", "Сервер не доступен"},



                    {"hello", "Привет!"},
                    {"windowClose", "Окно сообщений закрыто"},
                    {"err_login", "Неправильный логин или пароль! \nПопробуйте снова"},
                    {"suc_login", "Вы успешно вошли в систему!"},
                    {"suc_register", "Вы зарегистрированы! \nПопробуйте войти."},
                    {"err_register", "Пользователь с таким ником уже создан. \nПопробуйте снова!"},
                    {"Entry", "Вход"},
                    {"Info", "Информация"},
                    {"update", "Обновление"},
                    {"move", "Сместить"},
                    {"moveDragon", "Сместить Дракона"},
                    {"WriteId", "Введите ID"},

                    {"uPick", "Вы выбрали "},
                    {"DragonCreatedByUser", "Владелец: "},
                    {"with", "с"},
                    {"age", "Возраст: "},
                    {"Dname", "\nИмя: '"},
                    {"cooridanateX", ",\nКоординаты {x= "},
                    {"Dtype", "},\nТип: "},
                    {"DragonColor", "Цвет Дракона: "},
                    {"Dchar", ",\nХарактер: "},
                    {"Dcave", ",\nГлубина пещеры: "},
                    {"CreationTime", "\nПоследнее обновление: "},

            //Блок arg.fxml:
            {"InputData", "Входные данные"},
            //{"count_less_number", "Введите "},
            {"input_id", "Введите ID"},
            {"delete_id", "Удалить обьект"},
            {"input_depth", "Введите глубину пещеры."},
            {"add", "Добавить объект"},


            //Блок dragonDialog.fxml:
            {"parameters_dragon", "Введите параметры Dragon"},




            //Блок команд:
            {"suc_help", "remove_greater_key: Удалить из коллекции все элементы, ключ которых превышает заданный.\n" +
                    "clear: Очистить коллекцию (удаляет из коллекции элементы принадлежащие вам).\n" +
                    "show: Вывести в стандартный поток вывода все элементы коллекции в строковом представлении.\n" +
                    "insert: Добавить новый элемент с заданным ключом.\n" +
                    "update: Обновить значение элемента коллекции, id которого равен заданному.\n" +
                    "remove_greater: Удалить из коллекции все элементы, превышающие заданный.\n" +
                    "remove_lower: Удалить из коллекции все элементы, не превышающие заданный.\n" +
                    "remove_lower_key: Удалить из коллекции все элементы, меньшие заданного ключа.\n" +
                    "print_field_descending_character: Вывести элементы, сортируя по character.\n" +
                    "print_field_descending_type: Вывести элементы, сортируя по type.\n" +
                    "exit: Завершение работы программы без сохранения.\n" +
                    "help: Выводит справку по доступным командам.\n" +
                    "min_by_name: Вывести объект, минимального по имени.\n" +
                    "remove_key: Удалить элемент из коллекции по его id.\n" +
                    "info: Выводит в стандартный поток информацию о коллекции."},
            {"suc_info", "Информация: "},
            {"desc_char", "Элементов по Character в порядке убывания: "},
            {"desc_type", "Элементов по Type в порядке убывания: "},

            {"collection_empty", "Коллекция пуста!"},
            {"suc_insert", "Персонаж успешно добавлен!"},
            {"err_insert", "Персонаж уже существует с данным ключом."},
            {"suc_update", "Персонаж успешно обновлен с ID: "},
            {"err_update", "Персонаж не принадлежит вам с ID: "},
            {"err_update", "Данного персонажа нет в вашей коллекции"},
            {"suc_clear", "Коллекция успешно очищена! \nВсе элементы принадлежащие вам были удалены!"},
            {"suc_remove", "Персонаж удален с ID: "},
            {"err_remove", "Персонаж не найден или не принадлежит вам с ID: "},
            {"err_greater", "Персонаж уже существует с данным ID."},
            {"error_greater", "В коллекции нет персонажей превышающих ID: "},
            {"suc_greater", "Персонажи принадлежащие вам удалены с превышающим ID: "},
                    {"HMColl", "Количество элементов в коллекции"}, //*
                    {"suc_rlk", "Объекты, ниже заданного ключа, были удалены"},
                    {"suc_rg", "Объекты, выше заданного, были удалены"},
                    {"suc_rl", "Объекты, ниже заданного, были удалены"},
                    {"suc_script", "Скрипт исполнен"},




            //Блок Ошибок:
            {"Error", "Ошибка"},
            {"error_arg", "Команде не был указан аргумент или был неправильного формата!"},
            {"InvalidData", "Неверные данные команды!"},
            {"IncorrectField", "Неверно заполнены поля!"},
            {"IncorrectNumber", "Число вышло за диапазон!"},
            {"IncorrectDataField", "Неверно заполнено поле!"},
            {"ErrorCreatingObject", "Ошибка создания обьекта!"},
            {"UnexpectedException", "Неожиданное исключение!"},
            {"incorrectLogPas", "Некорректный Логин или Пароль!"},
            {"uncorrectLogPas", "Введите другой логин и пароль!"},
            {"idNotFound", "Персонаж с данным ID не найден!"},
            {"error_argDepth", "Неверный формат ввода! \n(Число должно быть больше или равно нулю)"},
            {"DB_NotAvailable", "База данных временно не доступна. Просим прощения за неудобства."}, //Добавлено
            {"dr_err", "Ошибка с объектом"},
            {"Wrong_input", "Неправильный ввод данных"},//*
            {"lessThen4", "Пароль должен состоять не менее чем из 5 символов"},
                    {"withSpaces", "В пароль не должны входить пробелы"},
                    {"invalidChars", "Пароль должен состоять только из английских букв и цифр"},

                    //Dragon Constructor
                    {"emptyName", "Пустое имя"},
                    {"InvalidAge", "Возраст должен быть задан в виде целого неотицательного числа"},
                    {"InvalidX", "Координата (X) должна быть задана в виде целого числа"},
                    {"InvalidY", "Координата (Y) должна быть задана в виде целого числа"},
                    {"emptyColor", "Нужно задать цвет (Color)"},
                    {"emptyChar", "Нужно задать характер (Character)"},
                    {"emptyType", "Нужно задать тип (Type)"},
                    {"InvalidCave", "Пещера должна быть задана в виде неотрицательного числа."},



            //Блок main.fxml:
            {"file", "Файл"},
            {"exit", "Выход"},

            {"help", "Помощь"},
            {"info", "Информация о коллекции"},
            {"helpCom", "Информация о командах"},

            {"language", "Выбрать язык"},

            {"username", "Пользователь: "},
            {"updateTable", "Обновить значения таблицы"},

            {"addCommands", "Команды Добавления:"},
            {"insertCom", "Добавить Дракона"},

            {"removeCommands", "Команды Удаления:"},
            {"removeID", "Удалить по ID"},
            {"removeLower", "Удалить меньших Драконов"},
            {"removeGreater", "Удалить больших Драконов"},
            {"remove_lower_key", "Удалить меньших ID"},//*
            {"clearCom", "Очистить"},

            {"updateCommands", "Команды Обновления:"},
            {"updateCom", "Обновить"},

            {"printCommands", "Команды Вывода:"},
            {"printDescendingType", "Вывести Драконов по типу"},
            {"printDescendingChar", "Вывести Дракона по характеру"},
            {"script", "Скрипт"}, //*

            {"tabDragon", "Таблица Dragon"},
            {"tabVisual", "Область визуализации"},
            {"",""},

            //script
                    {"dragon", "Дракон"},
                    {"param", "Параметр"},
                    {"command", "Команда"},
                    {"user", "Пользователь"},
                    {"commandManager", "Управление командами"},
                    {"delete", "Удалить"},
                    {"execute", "исполнить"},


    };
            public String getDate(LocalDateTime localDateTime) throws ParseException {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
                return localDateTime.format(formatter);
            }

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
