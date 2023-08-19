package Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Model.Cliente;
import Model.DetalleTransaccion;
import Model.Persona;
import Model.Producto;
import Model.TipoCliente;
import Model.TipoPaisOrigen;
import Model.TipoProducto;
import Application.Aplicacion;
import Exceptions.ClienteException;
import Exceptions.ProductoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

    @FXML
    private ImageView imageCliente;

	ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

	ObservableList <DetalleTransaccion> listaDetalles= FXCollections.observableArrayList();

	ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

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

    @FXML
    private Button btnAbrirFacturas;

	private Aplicacion aplicacion;

	private Stage stage;

	private Cliente clienteSeleccion;
	private Producto productoSeleccion;




//--------------------------------EVENTOS CLIENTES--------------------------------------------------------

    private ObservableList<Cliente> getClientes(){
    	listaClientes.clear();
    	listaClientes.addAll(singleton.getListaClientes());
    	return listaClientes;
    }

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
    		}else{
    			String nit = txtNit.getText();

    			if(validarDatos(nombre, apellidos, id, direccion, telefono,nit)){
    				crearClienteJuridico(nombre, apellidos, id, direccion, telefono, nit);
    				refrescarTableViewClientes();
    				limpiarCampos(event);
    			}
    		}

    	}else{
    		mostrarMensaje("Tipo de cliente no seleccionado", "Elija un tipo de cliente", "Asegurese de seleccionar un tipo de cliente", AlertType.INFORMATION);
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
    	}else{
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

	@FXML
    void eliminarCliente(ActionEvent event) throws ClienteException {
	  	if(clienteSeleccion!=null){
	  		try {
	    		int confirmacion= JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este cliente?");
	    		if(confirmacion==0){
		    		if(singleton.eliminarCliente(clienteSeleccion)){
		    			listaClientes.remove(clienteSeleccion);
		    			limpiarCampos(event);
		    			mostrarMensaje("Cliente eliminado", "Eliminacion de Cliente", "Se ha eliminado el Cliente con exito", AlertType.INFORMATION);
		    		}
	    		}
			} catch (ClienteException cException) {
				mostrarMensaje("Cliente no eliminado", "No se ha eliminado el cliente", cException.getMessage(), AlertType.INFORMATION);
			}
    	}else{
    		mostrarMensaje("Cliente seleccion", "Cliente Seleccion", "No se ha seleccionado ningun Cliente", AlertType.WARNING);
    	}
    }


//------------------------------------FUNCIONES ADMIN CLIENTES-----------------------------------------------

	private void refrescarTableViewClientes(){
		tableViewClientes.getItems().clear();
		tableViewClientes.setItems(getClientes());
	}

	private void crearClienteJuridico(String nombre, String apellidos, String id, String direccion, String telefono,
			String nit) throws ClienteException {

		try {
			boolean resultado = singleton.crearClienteJuridico(nombre,apellidos,id, direccion, telefono, nit);
			if(resultado==true){
				mostrarMensaje("Información Cliente", "Cliente creado", "El cliente se ha creado con éxito", AlertType.INFORMATION);
			}
		} catch (ClienteException cException) {
			mostrarMensaje("Cliente no creado", "Cliente sin registrar", cException.getMessage(), AlertType.WARNING);
		}
	}

	private void crearClienteNatural(String nombre, String apellidos, String id, String direccion, String telefono,
			String email, String fechaNacimiento) throws ClienteException {

		try {
			boolean resultado = singleton.crearClienteNatural(nombre,apellidos,id, direccion, telefono, email, fechaNacimiento);
			if(resultado){
				mostrarMensaje("Información Cliente", "Cliente creado", "El cliente se ha creado con éxito", AlertType.INFORMATION);
			}

		} catch (ClienteException  cException) {
			mostrarMensaje("Cliente no creado", "Cliente sin registrar", cException.getMessage(), AlertType.WARNING);
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

		if (direccion == null || direccion.equals("")) {
			notificacion += "Ingrese una dirección\n";
		}

		if (telefono == null || telefono.equals("")) {
			notificacion += "Ingrese una dirección\n";
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


//---------------------------------EVENTOS ADMIN PRODUCTOS ---------------------------------------------

	@FXML
	void listenerTipoProducto(ActionEvent event) {
		TipoProducto producto = comboBoxTipoProducto.getValue();
		if(producto!=null){
			switch (producto) {
			case REFRIGERADO:
				txtCodigoAprobacion.setDisable(false);
				txtTemperatura.setDisable(false);

				datePickerFechaEnvasado.setDisable(true);
				txtPesoEnvase.setDisable(true);
				comboBoxPaisOrigen.setDisable(true);
				datePickerFechaVencimiento.setDisable(true);

				break;
			case PERECEDERO:
				datePickerFechaVencimiento.setDisable(false);

				txtCodigoAprobacion.setDisable(true);
				txtTemperatura.setDisable(true);
				txtPesoEnvase.setDisable(true);
				comboBoxPaisOrigen.setDisable(true);
				datePickerFechaEnvasado.setDisable(true);

				break;

			default:
				datePickerFechaEnvasado.setDisable(false);
				txtPesoEnvase.setDisable(false);
				comboBoxPaisOrigen.setDisable(false);

				txtCodigoAprobacion.setDisable(true);
				txtTemperatura.setDisable(true);
				datePickerFechaVencimiento.setDisable(true);

				break;
			}
		}

	}


    @FXML
    void limpiarCamposProductos(ActionEvent event) {
    	txtNombreProducto.clear();
    	comboBoxTipoProducto.setValue(null);
    	txtCodigoProducto.clear();
    	txtValor.clear();
    	txtCantidad.clear();
    	txtDescripcion.clear();
    	txtPesoEnvase.clear();
    	datePickerFechaVencimiento.setValue(null);
		txtCodigoAprobacion.clear();
		txtTemperatura.clear();
		txtPesoEnvase.clear();
		comboBoxPaisOrigen.setValue(null);
		datePickerFechaVencimiento.setValue(null);

		datePickerFechaVencimiento.setDisable(true);
		txtCodigoAprobacion.setDisable(true);
		txtTemperatura.setDisable(true);
		txtPesoEnvase.setDisable(true);
		comboBoxPaisOrigen.setDisable(true);
		datePickerFechaEnvasado.setDisable(true);

    }

    @FXML
    void agregarProducto(ActionEvent event) throws ProductoException {
    	String nombreProducto = txtNombreProducto.getText();
    	String codigo = txtCodigoProducto.getText();
    	String valor = txtValor.getText();
    	String cantidad = txtCantidad.getText();
    	String descrp = txtDescripcion.getText();
    	TipoProducto tipoProduc = comboBoxTipoProducto.getValue();

    	if(tipoProduc!=null){
    		switch (tipoProduc) {
			case ENVASADO:
				LocalDate fechaEnvasado = datePickerFechaEnvasado.getValue();
				String pesoEnvase = txtPesoEnvase.getText();
				TipoPaisOrigen origenPais = comboBoxPaisOrigen.getValue();
				if(validarDatosProductos(nombreProducto, codigo, cantidad, descrp,valor, fechaEnvasado, pesoEnvase, origenPais)){
					String fechaEnva = fechaEnvasado.toString();
					crearProductoEnvasado(nombreProducto, codigo, cantidad, descrp, valor, fechaEnva, pesoEnvase,origenPais );
					refrescarTableViewProductos();
				}

				break;

			case REFRIGERADO:
				String codigoAprob = txtCodigoAprobacion.getText();
				String temperatura = txtTemperatura.getText();
				if(validarDatosProductos(nombreProducto, codigo, cantidad, descrp,valor, codigoAprob, temperatura))
					crearProductoRefrigerado(nombreProducto, codigo, cantidad, descrp,valor, codigoAprob, temperatura);
					refrescarTableViewProductos();
				break;

			default:
				LocalDate fechaVencimiento = datePickerFechaVencimiento.getValue();
				if(validarDatosProductos(nombreProducto, codigo, cantidad, descrp,valor, fechaVencimiento)){
					String fechaVenc = fechaVencimiento.toString();
					crearProductoPerecedero(nombreProducto, codigo, cantidad, descrp,valor, fechaVenc);
					refrescarTableViewProductos();
				}

				break;
			}
    		limpiarCamposProductos(event);
    	}
    	else{
    		mostrarMensaje("Notificación", "Tipo de producto no seleccionado",
    				"Asegurese de seleccionar un tipo de cliente", AlertType.INFORMATION);
    	}

    }


//------------------------------FUNCIONES PRODUCTOS----------------------------------------

    private void refrescarTableViewProductos(){
		tableViewProductos.getItems().clear();
		tableViewProductos.setItems(getProductos());
	}

	private ObservableList<Producto> getProductos(){
		listaProductos.clear();
		listaProductos.addAll(singleton.getListaProductos());
		return listaProductos;
	}


	private static String mezclarPalabra(String palabra) {
	        char[] letras = palabra.toCharArray();
	        Random random = new Random();

	        for (int i = 0; i < letras.length; i++) {
	            int indiceAleatorio = random.nextInt(letras.length);
	            char temp = letras[i];
	            letras[i] = letras[indiceAleatorio];
	            letras[indiceAleatorio] = temp;
	        }

	        return new String(letras);
	    }


	private boolean validarDatosProductos(String nombreProducto, String codigo, String cantidad, String descrp,
			String valor, LocalDate fechaVencimiento) {
    	String notificacion = "";
    	if (nombreProducto == null || nombreProducto.equals("")) {
			notificacion += "Ingrese el nombre del producto\n";
		}

		if (codigo == null || codigo.equals("")) {
			notificacion += "Ingrese el código del producto\n";
		}
		if (cantidad == null || cantidad.equals("")) {
			notificacion += "Ingrese la cantidad de productos\n";
		}

		if (descrp == null || descrp.equals("")) {
			notificacion += "Ingrese una descripción del producto\n";
		}
		if (valor == null || valor.equals("")) {
			notificacion += "Ingrese un valor unitario\n";
		}

		if (fechaVencimiento == null || fechaVencimiento.equals("")) {
			notificacion += "Seleccione la fecha de vencimiento\n";
		}

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Producto no agregado", notificacion, AlertType.WARNING);
			return false;
		}
		return true;
	}

	private boolean validarDatosProductos(String nombreProducto, String codigo, String cantidad, String descrp,
			String valor, LocalDate fechaEnvasado, String pesoEnvase, TipoPaisOrigen origenPais) {
    	String notificacion = "";
    	if (nombreProducto == null || nombreProducto.equals("")) {
			notificacion += "Ingrese el nombre del producto\n";
		}

		if (codigo == null || codigo.equals("")) {
			notificacion += "Ingrese el código del producto\n";
		}
		if (cantidad == null || cantidad.equals("")) {
			notificacion += "Ingrese la cantidad de productos\n";
		}

		if (descrp == null || descrp.equals("")) {
			notificacion += "Ingrese una descripción del producto\n";
		}
		if (valor == null || valor.equals("")) {
			notificacion += "Ingrese un valor unitario\n";
		}

		if (fechaEnvasado == null || fechaEnvasado.equals("")) {
			notificacion += "Ingrese la fecha de envasado\n";
		}

		if (pesoEnvase == null || pesoEnvase.equals("")) {
			notificacion += "Ingrese el peso del envase\n";
		}
		if (origenPais == null || origenPais.equals("")) {
			notificacion += "Elija un país de origen\n";
		}

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Producto no agregado", notificacion, AlertType.WARNING);
			return false;
		}
		return true;
	}
	private boolean validarDatosProductos(String nombreProducto, String codigo, String cantidad, String descrp, String valor,
			String codigoAprob, String temperatura) {
    	String notificacion = "";
    	if (nombreProducto == null || nombreProducto.equals("")) {
			notificacion += "Ingrese el nombre del producto\n";
		}

		if (codigo == null || codigo.equals("")) {
			notificacion += "Ingrese el código del producto\n";
		}
		if (cantidad == null || cantidad.equals("")) {
			notificacion += "Ingrese la cantidad de productos\n";
		}

		if (descrp == null || descrp.equals("")) {
			notificacion += "Ingrese una descripción del producto\n";
		}
		if (valor == null || valor.equals("")) {
			notificacion += "Ingrese un valor unitario\n";
		}

		if (codigoAprob == null || codigoAprob.equals("")) {
			notificacion += "Ingrese el código de aprobación\n";
		}

		if (temperatura == null || temperatura.equals("")) {
			notificacion += "Ingrese una temperatura de guardado\n";
		}

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Producto no agregado", notificacion, AlertType.WARNING);
			return false;
		}
		return true;
	}


	private void crearProductoEnvasado(String nombreProducto, String codigo, String cantidad, String descrp,
			String valor, String fechaEnvasado, String pesoEnvase, TipoPaisOrigen paisOrigen) throws ProductoException {

		try {
	    	boolean flag = singleton.crearProductoEnvasado(nombreProducto, codigo, cantidad, descrp, valor,
	    			fechaEnvasado, pesoEnvase, paisOrigen);
	       	if(flag==true){
	       		mostrarMensaje("Información Producto", "Producto agregado", "El Producto se ha agregado con éxito", AlertType.INFORMATION);
	       	}
		} catch (ProductoException pException) {
			mostrarMensaje("Producto no registrado", "No ha sido posible agregar el producto", pException.getMessage(), AlertType.INFORMATION);
		}

	}

	private void crearProductoRefrigerado(String nombreProducto, String codigo, String cantidad, String descrp, String valor,
			String codigoAprob, String temperatura) throws ProductoException {
		try {
	    	boolean flag = singleton.crearProductoRefrigerado(nombreProducto, codigo, cantidad, descrp, valor,
	   			 codigoAprob, temperatura);
	       	if(flag==true){
	       		mostrarMensaje("Información Producto", "Producto agregado", "El Producto se ha agregado con éxito", AlertType.INFORMATION);
	       	}
		} catch (ProductoException pException) {
			mostrarMensaje("Producto no registrado", "No ha sido posible agregar el producto", pException.getMessage(), AlertType.INFORMATION);
		}

	}

    private void crearProductoPerecedero(String nombreProducto, String codigo, String cantidad, String descrp,
			String valor, String fechaVencimiento) throws ProductoException {
    	try {
	    	boolean flag = singleton.crearProductoPerecedero(nombreProducto, codigo, cantidad, descrp, valor,
	    			fechaVencimiento);
	       	if(flag==true){
	       		mostrarMensaje("Información Producto", "Producto agregado", "El Producto se ha agregado con éxito", AlertType.INFORMATION);
	       	}
		} catch (ProductoException pException) {
			mostrarMensaje("Producto no registrado", "No ha sido posible agregar el producto", pException.getMessage(), AlertType.INFORMATION);
		}

	}


//----------------------------EVENTOS LISTA PRODUCTOS-------------------------------------------------------


    private TransaccionController createTransaccionControllerImplementation() {
        return new TransaccionController() {
            @Override
            public TableColumn<DetalleTransaccion, Integer> getColumCantidad() {
                TableColumn<DetalleTransaccion, Integer> columCantidad = new TableColumn<>("Cantidad");
                columCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
                return columCantidad;
            }

            @Override
            public TableColumn<DetalleTransaccion, Producto> getColumProducto() {
                TableColumn<DetalleTransaccion, Producto> columProducto = new TableColumn<>("Producto");
                columProducto.setCellValueFactory(new PropertyValueFactory<>("productoVendido"));
                return columProducto;
            }

            @Override
            public TableColumn<DetalleTransaccion, Double> getColumSubtotal() {
                TableColumn<DetalleTransaccion, Double> columSubtotal = new TableColumn<>("Subtotal");
                columSubtotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
                return columSubtotal;
            }

            @Override
            public TableView<DetalleTransaccion> getTableViewDetalle() {
                TableView<DetalleTransaccion> tableViewDetalle = new TableView<>();

                TableColumn<DetalleTransaccion, Integer> columCantidad = getColumCantidad();
                TableColumn<DetalleTransaccion, Producto> columProducto = getColumProducto();
                TableColumn<DetalleTransaccion, Double> columSubtotal = getColumSubtotal();

                tableViewDetalle.getColumns().addAll(columCantidad, columProducto, columSubtotal);

                return tableViewDetalle;
            }
        };
    }


    private ObservableList<DetalleTransaccion> getListaDetalles(List<DetalleTransaccion> listaDetallesTransaccion){
//		List<DetalleTransaccion> listaDetallesTransaccion= new ArrayList<>();

//    	Producto productoVenta= productoSeleccion;
//    	Double subTotal= productoVenta.getValorUnitario()*cantidad;
//		DetalleTransaccion detalle= new DetalleTransaccion(cantidad, subTotal, productoVenta);

//		listaDetallesTransaccion.add(detalle);
    	listaDetalles.clear();
    	listaDetalles.addAll(listaDetallesTransaccion);
    	return listaDetalles;

    }


    private void actualizarListaDetalles(Integer cantidad, Double subTotal) {
        Producto productoVenta = productoSeleccion;
        DetalleTransaccion detalle = new DetalleTransaccion(cantidad, subTotal, productoVenta);
        listaDetalles.add(detalle);
    	TransaccionController transaccionVenta = createTransaccionControllerImplementation();

    	System.out.println(listaDetalles.toString());


        transaccionVenta.getTableViewDetalle().getItems().setAll(listaDetalles);
    }


    @FXML
      void venderProducto(ActionEvent event) throws ProductoException {


  		if (productoSeleccion!=null) {
  			Integer cantidadVender = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad a vender"));
  	        Producto productoVenta = productoSeleccion;
  	        Double subTotal = productoVenta.getValorUnitario() * cantidadVender;

//  	    	Double subTotal= productoVenta.getValorUnitario()*cantidadVender;
//  			DetalleTransaccion detalle= new DetalleTransaccion(cantidadVender, subTotal, productoVenta);
//  			List<DetalleTransaccion> listaDetallesTransaccion= new ArrayList<>();

  			/**
  			 * Clases anonimas para evitar el nullPointerException
  			 */
  			TransaccionController transaccionVenta = createTransaccionControllerImplementation();
//  			transaccionVenta.getColumCantidad().setText(cantidadVender+"");
//  			transaccionVenta.getColumProducto().setText(productoVenta+"");
//  			transaccionVenta.getColumSubtotal().setText(subTotal+"");
//  			transaccionVenta.getTableViewDetalle().getItems().clear();
//  			transaccionVenta.getTableViewDetalle().setItems(getListaDetalles());

  	        if (singleton.venderProducto(productoSeleccion)) {
  	            System.out.println("VENDIDO");
  	            actualizarListaDetalles(cantidadVender, subTotal);
  	            mostrarMensaje("Producto vendido", "Producto vendido con exito", "El producto ha sido vendido con exito", AlertType.INFORMATION);
  	        } else {
  	            System.out.println("No vendido");
  	        }
		}else {
			mostrarMensaje("Producto seleccion", "Producto no seleccionado", "No ha seleccionado ningun producto para vender", AlertType.INFORMATION);
		}

      }



      @FXML
      void eliminarProducto(ActionEvent event)throws ProductoException {
    	  if (productoSeleccion!=null) {
    		  try {
    			  int confirmacion= JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este cliente");
    			  if (confirmacion==0) {
    				  if (singleton.eliminarProducto(productoSeleccion)) {
    					  listaProductos.remove(productoSeleccion);
    					  limpiarCamposProductos(event);
    					  mostrarMensaje("Eliminacion producto", "Producto eliminado", "Se ha eliminado el producto con exito", AlertType.INFORMATION);
					}
				}
			} catch (ProductoException pException) {
				mostrarMensaje("Eliminación producto", "Producto no eliminado", pException.getMessage(), AlertType.INFORMATION);
			}
		}else{
			mostrarMensaje("Selección producto", "Producto no seleccionado", "No ha seleccionado ningun producto para eliminar", AlertType.INFORMATION);
		}
      }

     @FXML
     	void showFacturasView(ActionEvent event) throws IOException, ProductoException{
	  		FXMLLoader loader= new FXMLLoader();
	 		loader.setLocation(Aplicacion.class.getResource("../Views/TransaccionView.fxml"));
	 		TabPane tabPane= (TabPane)loader.load();
	 		TransaccionController transaccionController = loader.getController();
	 		transaccionController.setAplicacion(aplicacion);
	 		Scene scene= new Scene(tabPane);
	 		Stage stage = new Stage();
	 		stage.setScene(scene);
	 		stage.setTitle("Facturacion");
	 		transaccionController.init(stage, this);
	 		String codigo= mezclarPalabra("ABCDEFGH01234589");
	 		LocalDate fecha=LocalDate.now();

	 		transaccionController.getColumSubtotal().setCellValueFactory(cellData -> cellData.getValue().getSubtotalProperty().asObject());
	 		Double total= transaccionController.getTableViewDetalle().getItems().stream().mapToDouble(DetalleTransaccion::getSubTotal).sum();

	 		float iva= (float) (total / 1.19);
	 		//Setear los campos de texto de la transaccion
	 		transaccionController.getTxtCodigo().setText(codigo);
	 		transaccionController.getDatePickerFecha().setValue(fecha);
	 		transaccionController.getTxtIva().setText(iva+"");
	 		transaccionController.getTxtTotal().setText(total+"");

	 		transaccionController.getTxtCodigo().setEditable(false);
	 		transaccionController.getTxtIva().setEditable(false);
	 		transaccionController.getTxtTotal().setEditable(false);
	 		transaccionController.getDatePickerFecha().setEditable(false);

//	 		transaccionController.getTableViewDetalle().setItems(getListaDetalles());


	 		stage.show();
	 		this.stage.close();

     }

	private boolean esNumero(String string) {
		try {

			Float.parseFloat(string);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//----------------------------FUNCIONES UTILITARIAS ----------------------------------------

	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertype) {
		Alert alert = new Alert(alertype);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

	//------------------------------JAVA FX-----------------------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBoxTipoCliente.getItems().addAll(TipoCliente.JURIDICO, TipoCliente.NATURAL);
		txtEmail.setDisable(true);
		datePickerFechaNacimiento.setDisable(true);
		txtNit.setDisable(true);

		/**
		 * Inicializacion de las tabla de clientes
		 */
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
		 * Funcion que hace que en el campo de texto solo se puedan usar caracteres numéricos
		 */
		TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 0,
                c -> c.getControlNewText().matches("\\d*") ? c : null);
        txtTelefono.setTextFormatter(textFormatter);
        TextFormatter<Integer> textFormatter1 = new TextFormatter<>(new IntegerStringConverter(), 0,
                c -> c.getControlNewText().matches("\\d*") ? c : null);
        txtNit.setTextFormatter(textFormatter1);
        TextFormatter<Integer> textFormatter2 = new TextFormatter<>(new IntegerStringConverter(), 0,
        		c -> c.getControlNewText().matches("\\d*") ? c : null);
        txtIdentificacion.setTextFormatter(textFormatter2);

        //PRODUCTOS
        comboBoxTipoProducto.getItems().addAll(TipoProducto.values());
        comboBoxPaisOrigen.getItems().addAll(TipoPaisOrigen.values());
        /**
         *Inicializacion tabla de los productos
         *
         */
        tableViewProductos.getItems().setAll(listaProductos);
        this.columNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		this.columValor.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
		this.columCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadExistencia"));
		this.columDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

		tableViewProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null){
				productoSeleccion= newSelection;
				txtNombreProducto.setText(productoSeleccion.getNombre());
				txtCodigoProducto.setText(productoSeleccion.getCodigo());
				txtValor.setText(productoSeleccion.getValorUnitario()+"");
				txtCantidad.setText(productoSeleccion.getCantidadExistencia()+"");
				txtDescripcion.setText(productoSeleccion.getDescripcion());
			}
		});


        datePickerFechaVencimiento.setDisable(true);
		txtCodigoAprobacion.setDisable(true);
		txtTemperatura.setDisable(true);
		txtPesoEnvase.setDisable(true);
		comboBoxPaisOrigen.setDisable(true);
		datePickerFechaEnvasado.setDisable(true);

		//FORMATS FIELD OF TEXTS------------------------------------------------------------------------
		TextFormatter<Integer> textFormatter3 = new TextFormatter<>(new IntegerStringConverter(), 0,
        		c -> c.getControlNewText().matches("\\d*") ? c : null);

		TextFormatter<Integer> textFormatter4 = new TextFormatter<>(new IntegerStringConverter(), 0,
        		c -> c.getControlNewText().matches("\\d*") ? c : null);
		TextFormatter<Integer> textFormatter5 = new TextFormatter<>(new IntegerStringConverter(), 0,
        		c -> c.getControlNewText().matches("\\d*") ? c : null);
		TextFormatter<Integer> textFormatter6 = new TextFormatter<>(new IntegerStringConverter(), 0,
        		c -> c.getControlNewText().matches("\\d*") ? c : null);
		TextFormatter<Integer> textFormatter7 = new TextFormatter<>(new IntegerStringConverter(), 0,
        		c -> c.getControlNewText().matches("\\d*") ? c : null);


		txtValor.setTextFormatter(textFormatter3);
		txtCantidad.setTextFormatter(textFormatter4);
		txtPesoEnvase.setTextFormatter(textFormatter5);
		txtCodigoAprobacion.setTextFormatter(textFormatter6);
		txtTemperatura.setTextFormatter(textFormatter7);

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
}
