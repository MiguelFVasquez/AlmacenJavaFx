package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Application.Aplicacion;

import java.util.ArrayList;

import Model.Cliente;
import Model.DetalleTransaccion;
import Model.Producto;
import Model.Transaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.IntegerStringConverter;
import java.util.List;
import java.util.Observable;

public class TransaccionController implements Initializable{

	Singleton singleton= Singleton.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Transaccion> tableViewTransacciones;

    @FXML
    private TableColumn<Transaccion, Cliente> columCliente;

    @FXML
    private TableColumn<Transaccion, String> columCodigo;

    @FXML
    private TableColumn<Transaccion, Float> columIva;
    @FXML
    private TableColumn<Transaccion, String> columFecha;

    @FXML
    private TableColumn<Transaccion, Double> columTotal;


    @FXML
    private TableView<DetalleTransaccion> tableViewDetalle;

    @FXML
    private TableColumn<DetalleTransaccion, Integer> columCantidad;
    @FXML
    private TableColumn<DetalleTransaccion, Producto> columProducto;
    @FXML
    private TableColumn<DetalleTransaccion, Double> columSubtotal;

    @FXML
    private TextField txtIva;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtCedulaTransaccion;

    @FXML
    private TextField txtTotal;

    @FXML
    private DatePicker datePickerFecha;


    @FXML
    private Button btnGenerarFactura;

    @FXML
    private Button btnEliminar;

    ObservableList<Transaccion> listaTransacciones= FXCollections.observableArrayList();

	private AlmacenController almacenController;

	private Stage stage;

	private Aplicacion aplicacion;


    private ObservableList<Transaccion> getListaTransacciones(){
    	listaTransacciones.clear();
    	listaTransacciones.addAll(singleton.getListaTransacciones());
    	return listaTransacciones;
    }


	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertype) {
		Alert alert = new Alert(alertype);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

    private boolean validarDatos(String codigo, String fecha, String cedula, String iva, String total){
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


    private void refrescarListaTransacciones(){
    	tableViewTransacciones.getItems().clear();
    	tableViewTransacciones.setItems(getListaTransacciones());
    }


    @FXML
    void generarFactura(ActionEvent event) {
    	String codigo= txtCodigo.getText();
    	String fecha= datePickerFecha.getValue().toString();
    	String cedula= txtCedulaTransaccion.getText();
    	String iva= txtIva.getText();
    	String total= txtTotal.getText();

    	Double subTotal = Double.parseDouble(columSubtotal.getText());
    	Integer cantidadDetalle= Integer.parseInt(columCantidad.getText());
    	Producto productoVenta= (Producto) columProducto.getUserData();

    	DetalleTransaccion detalleTransaccion= new DetalleTransaccion(cantidadDetalle,subTotal,productoVenta);
    	List<DetalleTransaccion> listaDetalles= new ArrayList<>();
    	listaDetalles.add(detalleTransaccion);

    	if (validarDatos(codigo, fecha, cedula, iva, total)) {
    		crearTransaccion(codigo, fecha, cedula, iva, total, listaDetalles);
    		refrescarListaTransacciones();
    	}
    }

    private void crearTransaccion(String codigo, String fecha, String cedula, String iva, String total,List<DetalleTransaccion> listaDetalles ){
    	try {
    		boolean resultado= singleton.crearTransaccion(codigo, fecha, cedula, total, iva, listaDetalles);
    				if (resultado) {
    					mostrarMensaje("Información transacción", "Transaccion generada", "La transacción se ha generado con exito", AlertType.INFORMATION);
					}
		} catch (Exception e) {
		}
    }


    @FXML
    void eliminarTransaccion(ActionEvent event) {

    }

    @FXML
    void volver(ActionEvent event) {
    	this.stage.close();
    	almacenController.show();
    }




	public TableView<DetalleTransaccion> getTableViewDetalle() {
		return tableViewDetalle;
	}


	public void setTableViewDetalle(TableView<DetalleTransaccion> tableViewDetalle) {
		this.tableViewDetalle = tableViewDetalle;
	}


	public TextField getTxtIva() {
		return txtIva;
	}


	public void setTxtIva(TextField txtIva) {
		this.txtIva = txtIva;
	}


	public TextField getTxtCodigo() {
		return txtCodigo;
	}


	public void setTxtCodigo(TextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}


	public TextField getTxtTotal() {
		return txtTotal;
	}


	public void setTxtTotal(TextField txtTotal) {
		this.txtTotal = txtTotal;
	}


	public DatePicker getDatePickerFecha() {
		return datePickerFecha;
	}


	public void setDatePickerFecha(DatePicker datePickerFecha) {
		this.datePickerFecha = datePickerFecha;
	}


	public TableColumn<DetalleTransaccion, Integer> getColumCantidad() {
		return columCantidad;
	}


	public void setColumCantidad(TableColumn<DetalleTransaccion, Integer> columCantidad) {
		this.columCantidad = columCantidad;
	}


	public TableColumn<DetalleTransaccion, Producto> getColumProducto() {
		return columProducto;
	}


	public void setColumProducto(TableColumn<DetalleTransaccion, Producto> columProducto) {
		this.columProducto = columProducto;
	}


	public TableColumn<DetalleTransaccion, Double> getColumSubtotal() {
		return columSubtotal;
	}


	public void setColumSubtotal(TableColumn<DetalleTransaccion, Double> columSubtotal) {
		this.columSubtotal = columSubtotal;
	}


	public void init(Stage stage, AlmacenController principalController) {
		this.almacenController = principalController;
		this.stage = stage;
	}

    public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion= aplicacion;
	}


    @FXML
    void initialize() {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.columCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
		this.columProducto.setCellValueFactory(new PropertyValueFactory<>("productoVendido"));
		this.columSubtotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));




		TextFormatter<Integer> textFormatter3 = new TextFormatter<>(new IntegerStringConverter(), 0,
        		c -> c.getControlNewText().matches("\\d*") ? c : null);
		txtTotal.setTextFormatter(textFormatter3);
		//txtIva.setTextFormatter(textFormatter3);







	}
}
