package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Model.Cliente;
import Model.Persona;
import Model.Producto;
import Model.TipoCliente;
import Model.TipoPaisOrigen;
import Model.TipoProducto;
import Application.Aplicacion;
import Exceptions.ClienteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class AlmacenController implements Initializable{

	Singleton singleton = Singleton.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

//---------------ELEMENTOS SOBRE EL CLIENTE-------------------------------------
    @FXML
    private ComboBox<TipoCliente> comboBoxTipoCliente;
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNit;

    @FXML
    private DatePicker datePickerFechaNacimiento;


    @FXML
    private TableView<Cliente> tableViewClientes;

    @FXML
    private TableColumn<Cliente, String> columNombreCliente;

    @FXML
    private TableColumn<Cliente, String> columApellidoCliente;

    @FXML
    private TableColumn<Cliente, String> columIdentificacionCliente;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnAniadir;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnActualizar;

	ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

//--------------------ELEMENTO DE LOS PRODUCTOS----------------------

    @FXML
    private Button btnNuevoProducto;

    @FXML
    private ComboBox<TipoProducto> comboBoxTipoProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtCodigoProducto;
    @FXML
    private TextField txtValor;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtPesoEnvase;
    @FXML
    private TextField txtCodigoAprobacion;
    @FXML
    private TextField txtTemperatura;
    @FXML
    private DatePicker datePickerFechaVencimiento;
    @FXML
    private DatePicker datePickerFechaEnvasado;
    @FXML
    private ComboBox<TipoPaisOrigen> comboBoxPaisOrigen;


    @FXML
    private TableView<Producto> tableViewProductos;

    @FXML
    private TableColumn<Producto, String> columNombreProducto;
    @FXML
    private TableColumn<Producto, String> columCodigo;
    @FXML
    private TableColumn<Producto, String> columDescripcion;

    @FXML
    private TableColumn<Producto, Integer> columCantidad;
    @FXML
    private TableColumn<Producto, Double> columValor;

    @FXML
    private Button btnVender;

    @FXML
    private Button btnAniadirProducto;

    @FXML
    private Button btnEliminarProducto;

	private Aplicacion aplicacion;

	private Stage stage;

	private Cliente clienteSeleccion;

    private ObservableList<Cliente> getClientes(){
    	listaClientes.clear();
    	listaClientes.addAll(singleton.getListaClientes());
    	return listaClientes;
    }


//--------------------------------EVENTOS--------------------------------------------------------

    @FXML
	void tipoClienteSeleccionado(ActionEvent event) {
		TipoCliente cliente = comboBoxTipoCliente.getValue();
		if(cliente!=null){
			if(cliente==TipoCliente.NATURAL){
				txtEmail.setDisable(false);
				datePickerFechaNacimiento.setDisable(false);
				txtNit.setDisable(true);
			}
			else{
				txtNit.setDisable(false);
				txtEmail.setDisable(true);
				datePickerFechaNacimiento.setDisable(true);
			}
		}

	}

	private boolean validarDatos(String nombre, String apellidos, String id, String direccion, String telefono,
			String email, String fechaNacimiento) {
		String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


		if (nombre == null || nombre.equals("")) {
			notificacion += "Ingrese su nombre\n";
		}

		if (apellidos == null || apellidos.equals("")) {
			notificacion += "Ingrese sus apellidos\n";
		}
		if (id == null || id.equals("")) {
			notificacion += "Ingrese una identificación\n";
		}
		else {
			if(!esNumero(id)){
				notificacion += "La identificación ingresada debe ser numérica\n";
			}
		}

		if (direccion == null || direccion.equals("")) {
			notificacion += "Ingrese una dirección\n";
		}

		if (telefono == null || telefono.equals("")) {
			notificacion += "Ingrese una dirección\n";
		}
		else {
			if(!esNumero(telefono)){
				notificacion += "La número de teléfono no son letras\n";
			}
		}

		if (email == null || email.equals("")) {
			notificacion += "Ingrese un email\n";
		}

		if (fechaNacimiento == null || fechaNacimiento.equals("")) {
			notificacion += "Seleccione una fecha de nacimiento\n";
		}

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Cliente no creado", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}

	private boolean esNumero(String string) {

		try {

			Float.parseFloat(string);

			return true;
		} catch (Exception e) {
			return false;
		}
	}



	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertype) {
		Alert alert = new Alert(alertype);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}



    @FXML
    void agregarCliente(ActionEvent event) throws ClienteException {
    	String nombre = txtNombre.getText();
    	String apellidos = txtApellido.getText();
    	String id = txtIdentificacion.getText();
    	String direccion = txtDireccion.getText();
    	String telefono = txtTelefono.getText();
    	TipoCliente tipoCliente = comboBoxTipoCliente.getValue();

    	if(tipoCliente!=null){
    		if(tipoCliente == TipoCliente.NATURAL){
    			String email = txtEmail.getText();
    			String fechaNacimiento = datePickerFechaNacimiento.getValue().toString();

    			if(validarDatos(nombre, apellidos, id, direccion, telefono, email, fechaNacimiento)){
    				crearClienteNatural(nombre, apellidos, id, direccion, telefono, email, fechaNacimiento);
    				refrescarTableViewClientes();
    				limpiarCampos(event);
    			}
    		}
    		else{
    			String nit = txtNit.getText();

    			if(validarDatos(nombre, apellidos, id, direccion, telefono,nit)){
    				crearClienteJuridico(nombre, apellidos, id, direccion, telefono, nit);
    				refrescarTableViewClientes();
    				limpiarCampos(event);
    			}
    		}


    	}
    	else{
    		mostrarMensaje("Tipo de cliente no seleccionado", "Elija un tipo de cliente", "Asegurese de seleccionar un tipo de cliente", AlertType.INFORMATION);
    	}


    }

    private void refrescarTableViewClientes(){
    	tableViewClientes.getItems().clear();
		tableViewClientes.setItems(getClientes());
    }

    private void crearClienteJuridico(String nombre, String apellidos, String id, String direccion, String telefono,
			String nit) throws ClienteException {
    	boolean resultado = singleton.crearClienteJuridico(nombre,apellidos,id, direccion, telefono, nit);
    	if(resultado==true){
    		mostrarMensaje("Información Cliente", "Cliente creado", "El cliente se ha creado con éxito", AlertType.INFORMATION);
    	}
    	else{
    		mostrarMensaje("Información Cliente", "Cliente no creado", "El cliente no ha sido creado", AlertType.INFORMATION);

    	}
	}


	private boolean validarDatos(String nombre, String apellidos, String id, String direccion, String telefono,
		String nit) {
    	String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


		if (nombre == null || nombre.equals("")) {
			notificacion += "Ingrese su nombre\n";
		}

		if (apellidos == null || apellidos.equals("")) {
			notificacion += "Ingrese sus apellidos\n";
		}
		if (id == null || id.equals("")) {
			notificacion += "Ingrese una identificación\n";
		}
		else {
			if(!esNumero(id)){
				notificacion += "La identificación ingresada debe ser numérica\n";
			}
		}

		if (direccion == null || direccion.equals("")) {
			notificacion += "Ingrese una dirección\n";
		}

		if (telefono == null || telefono.equals("")) {
			notificacion += "Ingrese una dirección\n";
		}
		else {
			if(!esNumero(telefono)){
				notificacion += "La número de teléfono no son letras\n";
			}
		}

		if (nit == null || nit.equals("")) {
			notificacion += "Ingrese un nit\n";
		}

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Cliente no creado", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
    }

	private void crearClienteNatural(String nombre, String apellidos, String id, String direccion, String telefono,
			String email, String fechaNacimiento) throws ClienteException {

        	boolean resultado = singleton.crearClienteNatural(nombre,apellidos,id, direccion, telefono, email, fechaNacimiento);
        	if(resultado==true){
        		mostrarMensaje("Información Cliente", "Cliente creado", "El cliente se ha creado con éxito", AlertType.INFORMATION);
        	}
        	else{
        		mostrarMensaje("Información Cliente", "Cliente no creado", "El cliente no ha sido creado", AlertType.INFORMATION);

        	}
    	}

	@FXML
    void limpiarCampos(ActionEvent event) {
		txtNombre.clear();
    	txtApellido.clear();
    	txtIdentificacion.clear();
    	txtDireccion.clear();
    	txtTelefono.clear();
    	comboBoxTipoCliente.setValue(null);
    	txtEmail.clear();
    	datePickerFechaNacimiento.setValue(null);
    	txtNit.clear();
    	txtEmail.setDisable(true);
		datePickerFechaNacimiento.setDisable(true);
		txtNit.setDisable(true);


    }

    @FXML
    void actualizarCliente(ActionEvent event) {
    	if(clienteSeleccion==null){
    		mostrarMensaje("Cliente seleccion", "Cliente Seleccion", "No se ha seleccionado ningun Cliente", AlertType.WARNING);
    	}
    	else{
    		String nombre = txtNombre.getText();
        	String apellido = txtApellido.getText();
        	String id = txtIdentificacion.getText();
        	String direccion = txtDireccion.getText();
        	String telefono = txtTelefono.getText();

        	if(validarDatos(nombre, apellido, id, direccion, telefono)){
        		clienteSeleccion.setNombre(nombre);
        		clienteSeleccion.setApellido(apellido);
        		clienteSeleccion.setIdentificacion(id);
        		clienteSeleccion.setDireccion(direccion);
        		clienteSeleccion.setTelefono(telefono);

        		limpiarCampos(event);
        		getClientes();
        		clienteSeleccion = null;
        		txtIdentificacion.setDisable(false);

        		mostrarMensaje("Actualización", "Cliente Actualizado con éxito"
        				, "La información del cliente fue actualizada con éxito"
        				, AlertType.INFORMATION);
        	}
    	}
    }


    private boolean validarDatos(String nombre, String apellido, String id, String direccion, String telefono) {
		String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


		if (nombre == null || nombre.equals("")) {
			notificacion += "Ingrese su nombre\n";
		}

		if (apellido == null || apellido.equals("")) {
			notificacion += "Ingrese sus apellidos\n";
		}
		if (id == null || id.equals("")) {
			notificacion += "Ingrese una identificación\n";
		}
		else {
			if(!esNumero(id)){
				notificacion += "La identificación ingresada debe ser numérica\n";
			}
		}

		if (direccion == null || direccion.equals("")) {
			notificacion += "Ingrese una dirección\n";
		}

		if (telefono == null || telefono.equals("")) {
			notificacion += "Ingrese una dirección\n";
		}
		else {
			if(!esNumero(telefono)){
				notificacion += "La número de teléfono no son letras\n";
			}
		}

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Cliente no actualiado", notificacion, AlertType.WARNING);
			return false;
		}

		return true;

	}


	@FXML
    void eliminarCliente(ActionEvent event) throws ClienteException {
	  	if(clienteSeleccion!=null){
    		int confirmacion= JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este cliente?");

    		if(confirmacion==0){

	    		if(singleton.eliminarCliente(clienteSeleccion)){
	    			listaClientes.remove(clienteSeleccion);
	    			limpiarCampos(event);
	    			mostrarMensaje("Cliente eliminado", "Eliminacion de Cliente", "Se ha eliminado el Cliente con exito", AlertType.INFORMATION);
	    		}
	    		else{
	    			mostrarMensaje("Cliente eliminado", "Eliminacion de Cliente", "No se ha podido eliminar el Cliente", AlertType.WARNING);
	    		}
    		}
    	}
    	else{
    		mostrarMensaje("Cliente seleccion", "Cliente Seleccion", "No se ha seleccionado ningun Cliente", AlertType.WARNING);
    	}
    }

    @FXML
    void limpiarCamposProductos(ActionEvent event) {

    }

    @FXML
    void agregarProducto(ActionEvent event) {

    }

    @FXML
    void venderProducto(ActionEvent event) {

    }

    @FXML
    void eliminarProducto(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion= aplicacion;
	}
	public void setStage(Stage primaryStage) {
		stage = primaryStage;
	}
    public void show() {
 		stage.show();
 	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBoxTipoCliente.getItems().addAll(TipoCliente.JURIDICO, TipoCliente.NATURAL);
		txtEmail.setDisable(true);
		datePickerFechaNacimiento.setDisable(true);
		txtNit.setDisable(true);

		tableViewClientes.getItems().setAll(listaClientes);

		this.columNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columApellidoCliente.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		this.columIdentificacionCliente.setCellValueFactory(new PropertyValueFactory<>("identificacion"));

		tableViewClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null){
					clienteSeleccion= newSelection;
					txtNombre.setText(clienteSeleccion.getNombre());
					txtApellido.setText(clienteSeleccion.getApellido());
					txtIdentificacion.setText(clienteSeleccion.getIdentificacion());
					txtDireccion.setText(clienteSeleccion.getDireccion());
					txtDireccion.setText(clienteSeleccion.getDireccion());
					txtTelefono.setText(clienteSeleccion.getTelefono());
					txtIdentificacion.setDisable(true);
					datePickerFechaNacimiento.setEditable(false);
					comboBoxTipoCliente.setDisable(true);

			}
		});

		/**
		 * Funcion que hace que en el campo de texto solo se puedan usar caracteres numericos
		 */
		TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 0,
                c -> c.getControlNewText().matches("\\d*") ? c : null);
        txtTelefono.setTextFormatter(textFormatter);

        TextFormatter<Integer> textFormatter1 = new TextFormatter<>(new IntegerStringConverter(), 0,
                c -> c.getControlNewText().matches("\\d*") ? c : null);
        txtNit.setTextFormatter(textFormatter1);


	}
}
