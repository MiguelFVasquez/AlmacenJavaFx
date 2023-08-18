package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TransaccionController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> columCliente;

    @FXML
    private TextField txtIva;

    @FXML
    private TableColumn<?, ?> columCodigo;

    @FXML
    private TableColumn<?, ?> columIva;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtCedulaTransaccion;

    @FXML
    private TextField txtTotal;

    @FXML
    private TableColumn<?, ?> columCantidad;

    @FXML
    private Button btnGenerarFactura;

    @FXML
    private TableColumn<?, ?> columFecha;

    @FXML
    private TableColumn<?, ?> columSubtotal;

    @FXML
    private TableColumn<?, ?> columTotal;

    @FXML
    private TableView<?> tableViewDetalle;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<?> tableViewTransacciones;

    @FXML
    private TextField txtFecha;

    @FXML
    private TableColumn<?, ?> columProducto;


	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertype) {
		Alert alert = new Alert(alertype);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

    private boolean validarDatos(String codigo, LocalDate fecha, String cedula, String iva, String total){
    	String notificacion= "";
    	if (codigo==null || codigo.equals("")) {
    		notificacion+= "Ingrese el código de la factura";
		}
    	if (fecha==null || codigo.equals("")) {
    		notificacion+= "Ingrese la fecha de la factura";
		}
    	if (cedula==null || cedula.equals("")) {
    		notificacion+= "Ingrese la cédula cel cliente";
		}
    	if (iva==null || iva.equals("")) {
    		notificacion+= "Ingrese el IVA";
		}
    	if (total==null || total.equals("")) {
    		notificacion+= "Ingrese el total de la factura";
		}
    	if (!notificacion.equals("")) {
    		mostrarMensaje("Notifacación Factura", "Factura no registrada", notificacion, AlertType.WARNING);
		}

    	return true;
    }



    @FXML
    void generarFactura(ActionEvent event) {

    }

    @FXML
    void eliminarTransaccion(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
