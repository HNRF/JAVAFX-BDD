module MaiCalendar.Proyecto {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	

    opens MaiCalendar.Proyecto to javafx.fxml;
    exports MaiCalendar.Proyecto;
    exports Modelo;
}
