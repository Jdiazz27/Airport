package core.view;

import core.controllers.FlightController;
import core.controllers.LocationController;
import core.controllers.PassengerController;
import core.controllers.PlaneController;
import core.controllers.tableLists.FlightTableList;
import core.controllers.tableLists.LocationTableList;
import core.controllers.tableLists.PassengerTableList;
import core.controllers.tableLists.PlaneTableList;
import core.controllers.tableLists.ShowMyFlightsTableList;
import core.controllers.tableLists.TableRefresherObserver;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.data.ComboLoader;
import core.data.JsonDataLoader;
import core.models.Flight;
import core.models.Location;
import core.models.Passenger;
import core.models.Plane;
import core.models.storage.AirportStorage;
import core.models.storage.FlightRepository;
import core.models.storage.LocationRepository;
import core.models.storage.PassengerRepository;
import core.models.storage.PlaneRepository;
import core.models.utils.FlightDelay;
import core.models.utils.FlightTimeCalculator;
import core.models.utils.PlaneCalculations;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edangulo
 */
public class AirportFrame extends javax.swing.JFrame {

    /**
     * Creates new form AirportFrame
     */
    private int x, y;
    private ArrayList<Passenger> passengers;
    private ArrayList<Plane> planes;
    private ArrayList<Location> locations;
    private ArrayList<Flight> flights;
    private LocationController locationController;
    private FlightRepository flightRP;
    private LocationRepository locationRP;
    private PlaneRepository planeRP;
    private PassengerRepository passengerRP;
    private PassengerController passengerController;

    JsonDataLoader loader = new JsonDataLoader();
    FlightTimeCalculator calculator = new FlightTimeCalculator();
    PlaneCalculations planeCalculator = new PlaneCalculations();
    FlightDelay delayFlight = new FlightDelay();

    public AirportFrame() {
        initComponents();

        this.passengers = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.flights = new ArrayList<>();
        this.flightRP = AirportStorage.getInstance().getFlightRepository();
        this.planeRP = AirportStorage.getInstance().getPlaneRepo();
        this.locationRP = AirportStorage.getInstance().getLocationRepository();
        this.passengerRP = AirportStorage.getInstance().getPassengerRepo();
        this.passengerController = new PassengerController(passengerRP);

        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);

        this.generateMonths();
        this.generateDays();
        this.generateHours();
        this.generateMinutes();
        this.blockPanels();
        loader.loadAllData();

        ComboLoader.loadPassengers(userSelect);
        ComboLoader.loadPlanes(PlaneFlight);
        ComboLoader.loadFlights(IDavion);
        ComboLoader.loadLocations(DepartureLocationFlight);
        ComboLoader.loadLocations(ArrivalLocationFlight);
        ComboLoader.loadLocations(ScaleFlight);
        ComboLoader.loadFlights(FlightAddToFrame);

        ScaleFlight.addItem("None");

        // Observadores de actualización de tablas
        TableRefresherObserver observerPassenger = new TableRefresherObserver(() -> {
            PassengerTableList.updatePassengersList((DefaultTableModel) ShowAllPassengers.getModel());
        });
        passengerRP.addObserver(observerPassenger);

        TableRefresherObserver observerPlane = new TableRefresherObserver(() -> {
            PlaneTableList.updatePlanesList((DefaultTableModel) ShowAllPlanes.getModel());
        });
        planeRP.addObserver(observerPlane);

        TableRefresherObserver observerLocation = new TableRefresherObserver(() -> {
            LocationTableList.updateLocationsList((DefaultTableModel) ShowAllLocations.getModel());
        });
        locationRP.addObserver(observerLocation);

        TableRefresherObserver observerFlight = new TableRefresherObserver(() -> {
            FlightTableList.updateFlightsList((DefaultTableModel) ShowAllFlights.getModel());
        });
        flightRP.addObserver(observerFlight);

        TableRefresherObserver observerShowMyFlight = new TableRefresherObserver(() -> {
            ShowMyFlightsTableList.updateShowMyFlights((DefaultTableModel) ShowMyFlights.getModel(), IdAddToFlight.getText());
        });
        flightRP.addObserver(observerShowMyFlight);

    }

    private void blockPanels() {
        //9, 11
        for (int i = 1; i < PanelDeOpciones.getTabCount(); i++) {
            if (i != 9 && i != 11) {
                PanelDeOpciones.setEnabledAt(i, false);
            }
        }
    }

    private void generateMonths() {
        for (int i = 1; i < 13; i++) {
            MONTH.addItem("" + i);
            MONTH1.addItem("" + i);
            MonthUpdate.addItem("" + i);
        }
    }

    private void generateDays() {
        for (int i = 1; i < 32; i++) {
            DAY.addItem("" + i);
            DAY1.addItem("" + i);
            DayUpdate.addItem("" + i);
        }
    }

    private void generateHours() {
        for (int i = 0; i < 24; i++) {
            HOUR.addItem("" + i);
            HOURLLEGADA.addItem("" + i);
            HOURSCALE.addItem("" + i);
            HOURSdelay.addItem("" + i);
        }
    }

    private void generateMinutes() {
        for (int i = 0; i < 60; i++) {
            MINUTE.addItem("" + i);
            MINUTELLEGADA.addItem("" + i);
            MINUTESCALE.addItem("" + i);
            MINUTESdelay.addItem("" + i);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new airport.PanelRound();
        panelRound2 = new airport.PanelRound();
        jButton13 = new javax.swing.JButton();
        PanelDeOpciones = new javax.swing.JTabbedPane();
        AdminPanel = new javax.swing.JPanel();
        user = new javax.swing.JRadioButton();
        administrator = new javax.swing.JRadioButton();
        userSelect = new javax.swing.JComboBox<>();
        PassengerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        IndicatorTx = new javax.swing.JTextField();
        IdPassengerTx = new javax.swing.JTextField();
        YearTx = new javax.swing.JTextField();
        CountryTx = new javax.swing.JTextField();
        PhoneTx = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        LastNameTx = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        MONTH = new javax.swing.JComboBox<>();
        FirstNameTx = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        DAY = new javax.swing.JComboBox<>();
        RegisterPassengerBtn = new javax.swing.JButton();
        AirplanePanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        IdAirplaneTx = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        BrandAirplaneTx = new javax.swing.JTextField();
        ModelAirplaneTx = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        MaxCapacityTx = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        AirlineTx = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        CreateAirplaneBtn = new javax.swing.JButton();
        LocationPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        AirportId = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        AirportName = new javax.swing.JTextField();
        AirportCity = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        AirportCountry = new javax.swing.JTextField();
        AirportLatitude = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        AirportLongitude = new javax.swing.JTextField();
        CreateLocationRegistration = new javax.swing.JButton();
        FlightPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        IdFLight = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        PlaneFlight = new javax.swing.JComboBox<>();
        DepartureLocationFlight = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        ArrivalLocationFlight = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        ScaleFlight = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        DepartureDateFlight = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        MONTH1 = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        DAY1 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        HOUR = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        MINUTE = new javax.swing.JComboBox<>();
        HOURLLEGADA = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        MINUTELLEGADA = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        HOURSCALE = new javax.swing.JComboBox<>();
        MINUTESCALE = new javax.swing.JComboBox<>();
        CreateFlightRegistration = new javax.swing.JButton();
        UpdatePanel = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        IdUpdate = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        FirstNameUpdate = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        LastNameUpdate = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        YearUpdate = new javax.swing.JTextField();
        MonthUpdate = new javax.swing.JComboBox<>();
        DayUpdate = new javax.swing.JComboBox<>();
        NumUpdate = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        IndicatorUpdate = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        CountryUpdate = new javax.swing.JTextField();
        UPADTE = new javax.swing.JButton();
        AddPanel = new javax.swing.JPanel();
        IdAddToFlight = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        FlightAddToFrame = new javax.swing.JComboBox<>();
        ADD = new javax.swing.JButton();
        ShowPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShowMyFlights = new javax.swing.JTable();
        REFRESH = new javax.swing.JButton();
        ShowPassengersPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ShowAllPassengers = new javax.swing.JTable();
        REFRESHSHOWALLPASSENGERS = new javax.swing.JButton();
        ShowFlightPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ShowAllFlights = new javax.swing.JTable();
        RefreshShowAllFlights = new javax.swing.JButton();
        ShowPlanesPanel = new javax.swing.JPanel();
        RefreshAllPlanes = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        ShowAllPlanes = new javax.swing.JTable();
        ShowLocationPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ShowAllLocations = new javax.swing.JTable();
        RefreshAllLocations = new javax.swing.JButton();
        DelayPanel = new javax.swing.JPanel();
        HOURSdelay = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        IDavion = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        MINUTESdelay = new javax.swing.JComboBox<>();
        DELAY = new javax.swing.JButton();
        panelRound3 = new airport.PanelRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelRound1.setRadius(40);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelRound2MouseDragged(evt);
            }
        });
        panelRound2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelRound2MousePressed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jButton13.setText("X");
        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(1083, Short.MAX_VALUE)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addComponent(jButton13)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

        PanelDeOpciones.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N

        AdminPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        user.setText("User");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        AdminPanel.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, -1, -1));

        administrator.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        administrator.setText("Administrator");
        administrator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administratorActionPerformed(evt);
            }
        });
        AdminPanel.add(administrator, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 164, -1, -1));

        userSelect.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        userSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select User" }));
        userSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userSelectActionPerformed(evt);
            }
        });
        AdminPanel.add(userSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 130, -1));

        PanelDeOpciones.addTab("Administration", AdminPanel);

        PassengerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel1.setText("Country:");
        PassengerPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel2.setText("ID:");
        PassengerPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel3.setText("First Name:");
        PassengerPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel4.setText("Last Name:");
        PassengerPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel5.setText("Birthdate:");
        PassengerPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel6.setText("+");
        PassengerPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 20, -1));

        IndicatorTx.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassengerPanel.add(IndicatorTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 50, -1));

        IdPassengerTx.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassengerPanel.add(IdPassengerTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 130, -1));

        YearTx.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassengerPanel.add(YearTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 90, -1));

        CountryTx.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassengerPanel.add(CountryTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 130, -1));

        PhoneTx.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassengerPanel.add(PhoneTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 130, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel7.setText("Phone:");
        PassengerPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel8.setText("-");
        PassengerPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 30, -1));

        LastNameTx.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassengerPanel.add(LastNameTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 130, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel9.setText("-");
        PassengerPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 30, -1));

        MONTH.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        MONTH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));
        PassengerPanel.add(MONTH, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        FirstNameTx.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassengerPanel.add(FirstNameTx, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 130, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel10.setText("-");
        PassengerPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 30, -1));

        DAY.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DAY.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));
        PassengerPanel.add(DAY, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, -1, -1));

        RegisterPassengerBtn.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RegisterPassengerBtn.setText("Register");
        RegisterPassengerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterPassengerBtnActionPerformed(evt);
            }
        });
        PassengerPanel.add(RegisterPassengerBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, -1, -1));

        PanelDeOpciones.addTab("Passenger registration", PassengerPanel);

        AirplanePanel.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel11.setText("ID:");
        AirplanePanel.add(jLabel11);
        jLabel11.setBounds(53, 96, 22, 25);

        IdAirplaneTx.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AirplanePanel.add(IdAirplaneTx);
        IdAirplaneTx.setBounds(180, 93, 130, 31);

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel12.setText("Brand:");
        AirplanePanel.add(jLabel12);
        jLabel12.setBounds(53, 157, 70, 25);

        BrandAirplaneTx.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AirplanePanel.add(BrandAirplaneTx);
        BrandAirplaneTx.setBounds(180, 154, 130, 31);

        ModelAirplaneTx.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AirplanePanel.add(ModelAirplaneTx);
        ModelAirplaneTx.setBounds(180, 213, 130, 31);

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setText("Model:");
        AirplanePanel.add(jLabel13);
        jLabel13.setBounds(53, 216, 60, 25);

        MaxCapacityTx.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AirplanePanel.add(MaxCapacityTx);
        MaxCapacityTx.setBounds(180, 273, 130, 31);

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel14.setText("Max Capacity:");
        AirplanePanel.add(jLabel14);
        jLabel14.setBounds(52, 276, 130, 25);

        AirlineTx.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        AirplanePanel.add(AirlineTx);
        AirlineTx.setBounds(180, 333, 130, 31);

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setText("Airline:");
        AirplanePanel.add(jLabel15);
        jLabel15.setBounds(53, 336, 70, 25);

        CreateAirplaneBtn.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        CreateAirplaneBtn.setText("Create");
        CreateAirplaneBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateAirplaneBtnActionPerformed(evt);
            }
        });
        AirplanePanel.add(CreateAirplaneBtn);
        CreateAirplaneBtn.setBounds(490, 480, 120, 40);

        PanelDeOpciones.addTab("Airplane registration", AirplanePanel);

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setText("Airport ID:");

        AirportId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel17.setText("Airport name:");

        AirportName.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        AirportCity.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel18.setText("Airport city:");

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel19.setText("Airport country:");

        AirportCountry.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        AirportLatitude.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel20.setText("Airport latitude:");

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel21.setText("Airport longitude:");

        AirportLongitude.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        CreateLocationRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        CreateLocationRegistration.setText("Create");
        CreateLocationRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateLocationRegistrationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LocationPanelLayout = new javax.swing.GroupLayout(LocationPanel);
        LocationPanel.setLayout(LocationPanelLayout);
        LocationPanelLayout.setHorizontalGroup(
            LocationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LocationPanelLayout.createSequentialGroup()
                .addGroup(LocationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LocationPanelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(LocationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(80, 80, 80)
                        .addGroup(LocationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AirportLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AirportId, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AirportName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AirportCity, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AirportCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AirportLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(LocationPanelLayout.createSequentialGroup()
                        .addGap(515, 515, 515)
                        .addComponent(CreateLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(515, 515, 515))
        );
        LocationPanelLayout.setVerticalGroup(
            LocationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LocationPanelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(LocationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(LocationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel17)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel18)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel19)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel20))
                    .addGroup(LocationPanelLayout.createSequentialGroup()
                        .addComponent(AirportId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(AirportName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(AirportCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(AirportCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(AirportLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(LocationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(AirportLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(CreateLocationRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        PanelDeOpciones.addTab("Location registration", LocationPanel);

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel22.setText("ID:");

        IdFLight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel23.setText("Plane:");

        PlaneFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PlaneFlight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plane" }));
        PlaneFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlaneFlightActionPerformed(evt);
            }
        });

        DepartureLocationFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DepartureLocationFlight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel24.setText("Departure location:");

        ArrivalLocationFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ArrivalLocationFlight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel25.setText("Arrival location:");

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel26.setText("Scale location:");

        ScaleFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ScaleFlight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));
        ScaleFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScaleFlightActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel27.setText("Duration:");

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel28.setText("Duration:");

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel29.setText("Departure date:");

        DepartureDateFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel30.setText("-");

        MONTH1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        MONTH1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel31.setText("-");

        DAY1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DAY1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        jLabel32.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel32.setText("-");

        HOUR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        HOUR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel33.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel33.setText("-");

        MINUTE.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        MINUTE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        HOURLLEGADA.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        HOURLLEGADA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel34.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel34.setText("-");

        MINUTELLEGADA.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        MINUTELLEGADA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        jLabel35.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel35.setText("-");

        HOURSCALE.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        HOURSCALE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));
        HOURSCALE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HOURSCALEActionPerformed(evt);
            }
        });

        MINUTESCALE.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        MINUTESCALE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        CreateFlightRegistration.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        CreateFlightRegistration.setText("Create");
        CreateFlightRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateFlightRegistrationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FlightPanelLayout = new javax.swing.GroupLayout(FlightPanel);
        FlightPanel.setLayout(FlightPanelLayout);
        FlightPanelLayout.setHorizontalGroup(
            FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FlightPanelLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(FlightPanelLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ScaleFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FlightPanelLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ArrivalLocationFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FlightPanelLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(46, 46, 46)
                        .addComponent(DepartureLocationFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FlightPanelLayout.createSequentialGroup()
                        .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IdFLight)
                            .addComponent(PlaneFlight, 0, 130, Short.MAX_VALUE))))
                .addGap(45, 45, 45)
                .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FlightPanelLayout.createSequentialGroup()
                        .addComponent(DepartureDateFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FlightPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(MONTH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(FlightPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(DAY1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FlightPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(HOUR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(FlightPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(MINUTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))
                    .addGroup(FlightPanelLayout.createSequentialGroup()
                        .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FlightPanelLayout.createSequentialGroup()
                                .addComponent(HOURLLEGADA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(FlightPanelLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(MINUTELLEGADA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(FlightPanelLayout.createSequentialGroup()
                                .addComponent(HOURSCALE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(FlightPanelLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(MINUTESCALE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FlightPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CreateFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(530, 530, 530))
        );
        FlightPanelLayout.setVerticalGroup(
            FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FlightPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FlightPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel22))
                    .addComponent(IdFLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(PlaneFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HOUR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(MINUTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FlightPanelLayout.createSequentialGroup()
                        .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24)
                                .addComponent(DepartureLocationFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel29))
                            .addComponent(DepartureDateFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MONTH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(DAY1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(ArrivalLocationFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28))
                            .addComponent(HOURLLEGADA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(MINUTELLEGADA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HOURSCALE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(MINUTESCALE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(FlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(ScaleFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(CreateFlightRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        PanelDeOpciones.addTab("Flight registration", FlightPanel);

        jLabel36.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel36.setText("ID:");

        IdUpdate.setEditable(false);
        IdUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        IdUpdate.setEnabled(false);

        jLabel37.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel37.setText("First Name:");

        FirstNameUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel38.setText("Last Name:");

        LastNameUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel39.setText("Birthdate:");

        YearUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        MonthUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        MonthUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        DayUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DayUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        NumUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel40.setText("-");

        IndicatorUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel41.setText("+");

        jLabel42.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel42.setText("Phone:");

        jLabel43.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel43.setText("Country:");

        CountryUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        UPADTE.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        UPADTE.setText("Update");
        UPADTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPADTEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UpdatePanelLayout = new javax.swing.GroupLayout(UpdatePanel);
        UpdatePanel.setLayout(UpdatePanelLayout);
        UpdatePanelLayout.setHorizontalGroup(
            UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdatePanelLayout.createSequentialGroup()
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdatePanelLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UpdatePanelLayout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(108, 108, 108)
                                .addComponent(IdUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(UpdatePanelLayout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(41, 41, 41)
                                .addComponent(FirstNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(UpdatePanelLayout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(43, 43, 43)
                                .addComponent(LastNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(UpdatePanelLayout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(55, 55, 55)
                                .addComponent(YearUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(MonthUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(DayUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(UpdatePanelLayout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(IndicatorUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(NumUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(UpdatePanelLayout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(63, 63, 63)
                                .addComponent(CountryUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(UpdatePanelLayout.createSequentialGroup()
                        .addGap(507, 507, 507)
                        .addComponent(UPADTE)))
                .addContainerGap(555, Short.MAX_VALUE))
        );
        UpdatePanelLayout.setVerticalGroup(
            UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdatePanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(IdUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(FirstNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(LastNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(YearUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MonthUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DayUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jLabel41)
                    .addComponent(IndicatorUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(NumUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(CountryUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(UPADTE)
                .addGap(113, 113, 113))
        );

        PanelDeOpciones.addTab("Update info", UpdatePanel);

        IdAddToFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        IdAddToFlight.setEnabled(false);

        jLabel44.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel44.setText("ID:");

        jLabel45.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel45.setText("Flight:");

        FlightAddToFrame.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        FlightAddToFrame.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Flight" }));
        FlightAddToFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FlightAddToFrameActionPerformed(evt);
            }
        });

        ADD.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ADD.setText("Add");
        ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddPanelLayout = new javax.swing.GroupLayout(AddPanel);
        AddPanel.setLayout(AddPanelLayout);
        AddPanelLayout.setHorizontalGroup(
            AddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddPanelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(AddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45))
                .addGap(79, 79, 79)
                .addGroup(AddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FlightAddToFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IdAddToFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(829, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ADD, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(509, 509, 509))
        );
        AddPanelLayout.setVerticalGroup(
            AddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(AddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel44))
                    .addComponent(IdAddToFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(AddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(FlightAddToFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(ADD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );

        PanelDeOpciones.addTab("Add to flight", AddPanel);

        ShowMyFlights.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ShowMyFlights.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Departure Date", "Arrival Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ShowMyFlights);

        REFRESH.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        REFRESH.setText("Refresh");
        REFRESH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REFRESHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ShowPanelLayout = new javax.swing.GroupLayout(ShowPanel);
        ShowPanel.setLayout(ShowPanelLayout);
        ShowPanelLayout.setHorizontalGroup(
            ShowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowPanelLayout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShowPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(REFRESH)
                .addGap(527, 527, 527))
        );
        ShowPanelLayout.setVerticalGroup(
            ShowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowPanelLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(REFRESH)
                .addContainerGap())
        );

        PanelDeOpciones.addTab("Show my flights", ShowPanel);

        ShowAllPassengers.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ShowAllPassengers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Birthdate", "Age", "Phone", "Country", "Num Flight"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ShowAllPassengers);

        REFRESHSHOWALLPASSENGERS.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        REFRESHSHOWALLPASSENGERS.setText("Refresh");
        REFRESHSHOWALLPASSENGERS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REFRESHSHOWALLPASSENGERSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ShowPassengersPanelLayout = new javax.swing.GroupLayout(ShowPassengersPanel);
        ShowPassengersPanel.setLayout(ShowPassengersPanelLayout);
        ShowPassengersPanelLayout.setHorizontalGroup(
            ShowPassengersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowPassengersPanelLayout.createSequentialGroup()
                .addGroup(ShowPassengersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ShowPassengersPanelLayout.createSequentialGroup()
                        .addGap(489, 489, 489)
                        .addComponent(REFRESHSHOWALLPASSENGERS))
                    .addGroup(ShowPassengersPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        ShowPassengersPanelLayout.setVerticalGroup(
            ShowPassengersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShowPassengersPanelLayout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(REFRESHSHOWALLPASSENGERS)
                .addContainerGap())
        );

        PanelDeOpciones.addTab("Show all passengers", ShowPassengersPanel);

        ShowAllFlights.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        ShowAllFlights.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Departure Airport ID", "Arrival Airport ID", "Scale Airport ID", "Departure Date", "Arrival Date", "Plane ID", "Number Passengers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(ShowAllFlights);

        RefreshShowAllFlights.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RefreshShowAllFlights.setText("Refresh");
        RefreshShowAllFlights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshShowAllFlightsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ShowFlightPanelLayout = new javax.swing.GroupLayout(ShowFlightPanel);
        ShowFlightPanel.setLayout(ShowFlightPanelLayout);
        ShowFlightPanelLayout.setHorizontalGroup(
            ShowFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowFlightPanelLayout.createSequentialGroup()
                .addGroup(ShowFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ShowFlightPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ShowFlightPanelLayout.createSequentialGroup()
                        .addGap(521, 521, 521)
                        .addComponent(RefreshShowAllFlights)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        ShowFlightPanelLayout.setVerticalGroup(
            ShowFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowFlightPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RefreshShowAllFlights)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        PanelDeOpciones.addTab("Show all flights", ShowFlightPanel);

        RefreshAllPlanes.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RefreshAllPlanes.setText("Refresh");
        RefreshAllPlanes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshAllPlanesActionPerformed(evt);
            }
        });

        ShowAllPlanes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Brand", "Model", "Max Capacity", "Airline", "Number Flights"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(ShowAllPlanes);

        javax.swing.GroupLayout ShowPlanesPanelLayout = new javax.swing.GroupLayout(ShowPlanesPanel);
        ShowPlanesPanel.setLayout(ShowPlanesPanelLayout);
        ShowPlanesPanelLayout.setHorizontalGroup(
            ShowPlanesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowPlanesPanelLayout.createSequentialGroup()
                .addGroup(ShowPlanesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ShowPlanesPanelLayout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(RefreshAllPlanes))
                    .addGroup(ShowPlanesPanelLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        ShowPlanesPanelLayout.setVerticalGroup(
            ShowPlanesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShowPlanesPanelLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(RefreshAllPlanes)
                .addGap(17, 17, 17))
        );

        PanelDeOpciones.addTab("Show all planes", ShowPlanesPanel);

        ShowAllLocations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Airport ID", "Airport Name", "City", "Country"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(ShowAllLocations);

        RefreshAllLocations.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        RefreshAllLocations.setText("Refresh");
        RefreshAllLocations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshAllLocationsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ShowLocationPanelLayout = new javax.swing.GroupLayout(ShowLocationPanel);
        ShowLocationPanel.setLayout(ShowLocationPanelLayout);
        ShowLocationPanelLayout.setHorizontalGroup(
            ShowLocationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowLocationPanelLayout.createSequentialGroup()
                .addGroup(ShowLocationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ShowLocationPanelLayout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(RefreshAllLocations))
                    .addGroup(ShowLocationPanelLayout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(272, Short.MAX_VALUE))
        );
        ShowLocationPanelLayout.setVerticalGroup(
            ShowLocationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShowLocationPanelLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(RefreshAllLocations)
                .addGap(17, 17, 17))
        );

        PanelDeOpciones.addTab("Show all locations", ShowLocationPanel);

        HOURSdelay.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        HOURSdelay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel46.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel46.setText("Hours:");

        jLabel47.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel47.setText("ID:");

        IDavion.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        IDavion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID" }));
        IDavion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDavionActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel48.setText("Minutes:");

        MINUTESdelay.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        MINUTESdelay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        DELAY.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        DELAY.setText("Delay");
        DELAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELAYActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DelayPanelLayout = new javax.swing.GroupLayout(DelayPanel);
        DelayPanel.setLayout(DelayPanelLayout);
        DelayPanelLayout.setHorizontalGroup(
            DelayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DelayPanelLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(DelayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DelayPanelLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(MINUTESdelay, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DelayPanelLayout.createSequentialGroup()
                        .addGroup(DelayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel46))
                        .addGap(79, 79, 79)
                        .addGroup(DelayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HOURSdelay, 0, 105, Short.MAX_VALUE)
                            .addComponent(IDavion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(820, 820, 820))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DelayPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DELAY)
                .addGap(531, 531, 531))
        );
        DelayPanelLayout.setVerticalGroup(
            DelayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DelayPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(DelayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(IDavion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(DelayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(HOURSdelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(DelayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(MINUTESdelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                .addComponent(DELAY)
                .addGap(33, 33, 33))
        );

        PanelDeOpciones.addTab("Delay flight", DelayPanel);

        panelRound1.add(PanelDeOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 41, 1150, 620));

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        panelRound1.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 660, 1150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelRound2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_panelRound2MousePressed

    private void panelRound2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_panelRound2MouseDragged

    private void administratorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administratorActionPerformed
        if (user.isSelected()) {
            user.setSelected(false);
            userSelect.setSelectedIndex(0);

        }
        for (int i = 1; i < PanelDeOpciones.getTabCount(); i++) {
            PanelDeOpciones.setEnabledAt(i, true);
        }
        PanelDeOpciones.setEnabledAt(5, false);
        PanelDeOpciones.setEnabledAt(6, false);
    }//GEN-LAST:event_administratorActionPerformed

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        if (administrator.isSelected()) {
            administrator.setSelected(false);
        }
        for (int i = 1; i < PanelDeOpciones.getTabCount(); i++) {

            PanelDeOpciones.setEnabledAt(i, false);

        }
        PanelDeOpciones.setEnabledAt(9, true);
        PanelDeOpciones.setEnabledAt(5, true);
        PanelDeOpciones.setEnabledAt(6, true);
        PanelDeOpciones.setEnabledAt(7, true);
        PanelDeOpciones.setEnabledAt(11, true);
    }//GEN-LAST:event_userActionPerformed

    private void RegisterPassengerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterPassengerBtnActionPerformed
        String id = IdPassengerTx.getText().trim();
        String firstName = FirstNameTx.getText().trim();
        String lastName = LastNameTx.getText().trim();
        String year = YearTx.getText().trim();
        String month = MONTH.getSelectedItem().toString();
        String day = DAY.getSelectedItem().toString();
        String phoneCode = IndicatorTx.getText().trim();
        String phone = PhoneTx.getText().trim();
        String country = CountryTx.getText().trim();
        String birthDate = year + "-" + month + "-" + day;

        Response response = passengerController.registerPassenger(id, firstName, lastName, birthDate, phoneCode, phone, country);
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Advertencia " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Éxito " + response.getStatus(), JOptionPane.INFORMATION_MESSAGE);

            IdPassengerTx.setText("");
            FirstNameTx.setText("");
            LastNameTx.setText("");
            YearTx.setText("");
            MONTH.setSelectedIndex(0);
            DAY.setSelectedIndex(0);
            PhoneTx.setText("");
            CountryTx.setText("");

            userSelect.addItem(id);
        }

    }//GEN-LAST:event_RegisterPassengerBtnActionPerformed

    private void CreateAirplaneBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateAirplaneBtnActionPerformed
        String id = IdAirplaneTx.getText();
        String brand = BrandAirplaneTx.getText();
        String model = ModelAirplaneTx.getText();
        String maxCapacity = MaxCapacityTx.getText();
        String airline = AirlineTx.getText();

        Response response = PlaneController.registerPlane(id, brand, model, maxCapacity, airline);

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Éxito " + response.getStatus(), JOptionPane.INFORMATION_MESSAGE);
            IdAirplaneTx.setText("");
            BrandAirplaneTx.setText("");
            ModelAirplaneTx.setText("");
            MaxCapacityTx.setText("");
            AirlineTx.setText("");
            PlaneFlight.addItem(id);
        }
    }//GEN-LAST:event_CreateAirplaneBtnActionPerformed

    private void CreateLocationRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateLocationRegistrationActionPerformed
        String id = AirportId.getText();
        String name = AirportName.getText();
        String city = AirportCity.getText();
        String country = AirportCountry.getText();
        String latitude = AirportLatitude.getText();
        String longitude = AirportLongitude.getText();

        LocationRepository locationRepository = AirportStorage.getInstance().getLocationRepository();
        LocationController locationController = new LocationController(locationRepository);

        Response response = locationController.createLocation(id, name, city, country, latitude, longitude);
        JOptionPane.showMessageDialog(null, response.getMessage());
        if (response.getStatus() == Status.CREATED) {
            AirportId.setText("");
            AirportName.setText("");
            AirportCity.setText("");
            AirportCountry.setText("");
            AirportLatitude.setText("");
            AirportLongitude.setText("");
            DepartureLocationFlight.addItem(id);
            ArrivalLocationFlight.addItem(id);
            ScaleFlight.addItem(id);
        }

    }//GEN-LAST:event_CreateLocationRegistrationActionPerformed

    private void CreateFlightRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateFlightRegistrationActionPerformed
        String vueloId = IdFLight.getText();
        String avionId = PlaneFlight.getItemAt(PlaneFlight.getSelectedIndex());
        String locSalidaId = DepartureLocationFlight.getItemAt(DepartureLocationFlight.getSelectedIndex());
        String locLlegadaId = ArrivalLocationFlight.getItemAt(ArrivalLocationFlight.getSelectedIndex());
        String locEscalaId = ScaleFlight.getItemAt(ScaleFlight.getSelectedIndex());
        String año = DepartureDateFlight.getText();
        String mesSalida = MONTH1.getItemAt(MONTH1.getSelectedIndex());
        String diaSalida = DAY1.getItemAt(DAY1.getSelectedIndex());
        String horaSalida = HOUR.getItemAt(HOUR.getSelectedIndex());
        String minutosSalida = MINUTE.getItemAt(MINUTE.getSelectedIndex());
        String horaLlegada = HOURLLEGADA.getItemAt(HOURLLEGADA.getSelectedIndex());
        String minutosLlegada = MINUTELLEGADA.getItemAt(MINUTELLEGADA.getSelectedIndex());
        String horaEscala = HOURSCALE.getItemAt(HOURSCALE.getSelectedIndex());
        String minutosEscala = MINUTESCALE.getItemAt(MINUTESCALE.getSelectedIndex());
        String fechaSalida = String.format("%s-%s-%s", año, mesSalida, diaSalida);
        String horaSalidaCompleta = String.format("%s:%s", horaSalida, minutosSalida);

        FlightController controladorVuelos = new FlightController(flightRP, locationRP, planeRP);
        Response respuesta = controladorVuelos.createFlight(
                vueloId, locSalidaId, locLlegadaId, fechaSalida, horaSalidaCompleta, horaLlegada, minutosLlegada, avionId, locEscalaId, horaEscala, minutosEscala
        );

        if (respuesta.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Error " + respuesta.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (respuesta.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Error " + respuesta.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Éxito " + respuesta.getStatus(), JOptionPane.INFORMATION_MESSAGE);
            IdFLight.setText("");
            YearTx.setText("");
            PlaneFlight.setSelectedIndex(0);
            DepartureLocationFlight.setSelectedIndex(0);
            ArrivalLocationFlight.setSelectedIndex(0);
            ScaleFlight.setSelectedIndex(0);
            MONTH1.setSelectedIndex(0);
            DAY1.setSelectedIndex(0);
            HOURLLEGADA.setSelectedIndex(0);
            MINUTELLEGADA.setSelectedIndex(0);
            HOURSCALE.setSelectedIndex(0);
            MINUTESCALE.setSelectedIndex(0);
            HOURSdelay.setSelectedIndex(0);
            MINUTESdelay.setSelectedIndex(0);
            PlaneFlight.addItem(vueloId);
            FlightAddToFrame.addItem(vueloId);
        }

    }//GEN-LAST:event_CreateFlightRegistrationActionPerformed

    private void UPADTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPADTEActionPerformed
        String idPasajero = IdUpdate.getText().trim();
        String nombre = FirstNameUpdate.getText().trim();
        String apellido = LastNameUpdate.getText().trim();
        String anio = YearUpdate.getText().trim();
        String mes = MonthUpdate.getItemAt(MonthUpdate.getSelectedIndex());
        String dia = DayUpdate.getItemAt(DayUpdate.getSelectedIndex());
        String codigoTelefono = IndicatorUpdate.getText().trim();
        String telefono = NumUpdate.getText().trim();
        String pais = CountryUpdate.getText().trim();
        String fechaNacimiento = anio + "-" + mes + "-" + dia;
        Response respuesta = passengerController.updatePassenger(
                idPasajero,
                nombre,
                apellido,
                fechaNacimiento,
                codigoTelefono,
                telefono,
                pais
        );

        if (respuesta.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Error " + respuesta.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (respuesta.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Error " + respuesta.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos tras actualización exitosa
            IdUpdate.setText("");
            FirstNameUpdate.setText("");
            LastNameUpdate.setText("");
            YearUpdate.setText("");
            MonthUpdate.setSelectedIndex(0);
            DayUpdate.setSelectedIndex(0);
            NumUpdate.setText("");
            IndicatorUpdate.setText("");
            CountryUpdate.setText("");
        }
    }//GEN-LAST:event_UPADTEActionPerformed

    private void ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDActionPerformed
        try {
            long idPasajeroVuelo = Long.parseLong(IdAddToFlight.getText().trim());
            String idVueloSeleccionado = FlightAddToFrame.getItemAt(FlightAddToFrame.getSelectedIndex());

            FlightController controladorVuelo = new FlightController(
                    AirportStorage.getInstance().getFlightRepository(),
                    AirportStorage.getInstance().getLocationRepository(),
                    AirportStorage.getInstance().getPlaneRepo()
            );

            Response respuesta = controladorVuelo.addPassengerToFlight(idPasajeroVuelo, idVueloSeleccionado);

            if (respuesta.getStatus() >= 500) {
                JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Error " + respuesta.getStatus(), JOptionPane.ERROR_MESSAGE);
            } else if (respuesta.getStatus() >= 400) {
                JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Advertencia " + respuesta.getStatus(), JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ADDActionPerformed

    private void DELAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELAYActionPerformed

        Object selectedFlight = IDavion.getSelectedItem();
        Object selectedHours = HOURSdelay.getSelectedItem();
        Object selectedMinutes = MINUTESdelay.getSelectedItem();

        if (selectedFlight == null || selectedHours == null || selectedMinutes == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar seleccionados.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String flightId = selectedFlight.toString();
        String hoursStr = selectedHours.toString();
        String minutesStr = selectedMinutes.toString();

        FlightController controller = new FlightController(flightRP, locationRP, planeRP);
        Response response = controller.delayFlight(flightId, hoursStr, minutesStr);

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Advertencia " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            DefaultTableModel model = (DefaultTableModel) ShowAllFlights.getModel();
            FlightTableList.updateFlightsList(model);
            IDavion.setSelectedIndex(0);
            HOURSdelay.setSelectedIndex(0);
            MINUTESdelay.setSelectedIndex(0);

        }

    }//GEN-LAST:event_DELAYActionPerformed

    private void REFRESHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REFRESHActionPerformed
        String passengerId = IdAddToFlight.getText();

        Response response = ShowMyFlightsTableList.updateShowMyFlights((DefaultTableModel) ShowMyFlights.getModel(), passengerId);

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Advertencia " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_REFRESHActionPerformed

    private void REFRESHSHOWALLPASSENGERSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REFRESHSHOWALLPASSENGERSActionPerformed
        Response response = PassengerTableList.updatePassengersList((DefaultTableModel) ShowAllPassengers.getModel());

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_REFRESHSHOWALLPASSENGERSActionPerformed

    private void RefreshShowAllFlightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshShowAllFlightsActionPerformed
        Response r = FlightTableList.updateFlightsList((DefaultTableModel) ShowAllFlights.getModel());

        if (r.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, r.getMessage(), "Error " + r.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (r.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, r.getMessage(), "Error " + r.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, r.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_RefreshShowAllFlightsActionPerformed

    private void RefreshAllPlanesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshAllPlanesActionPerformed
        Response r = FlightTableList.updateFlightsList((DefaultTableModel) ShowAllPlanes.getModel());

        if (r.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, r.getMessage(), "Error " + r.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (r.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, r.getMessage(), "Error " + r.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, r.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_RefreshAllPlanesActionPerformed

    private void RefreshAllLocationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshAllLocationsActionPerformed
        Response r = LocationTableList.updateLocationsList((DefaultTableModel) ShowAllLocations.getModel());

        if (r.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, r.getMessage(), "Error " + r.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (r.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, r.getMessage(), "Error " + r.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, r.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_RefreshAllLocationsActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void userSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userSelectActionPerformed
        try {
            String id = userSelect.getSelectedItem().toString();
            if (!id.equals(userSelect.getItemAt(0))) {
                IdUpdate.setText(id);
                IdAddToFlight.setText(id);
            } else {
                IdUpdate.setText("");
                IdAddToFlight.setText("");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_userSelectActionPerformed

    private void ScaleFlightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScaleFlightActionPerformed

    }//GEN-LAST:event_ScaleFlightActionPerformed

    private void HOURSCALEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HOURSCALEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HOURSCALEActionPerformed

    private void PlaneFlightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlaneFlightActionPerformed

    }//GEN-LAST:event_PlaneFlightActionPerformed

    private void IDavionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDavionActionPerformed
        String id = IDavion.getSelectedItem().toString();
    }//GEN-LAST:event_IDavionActionPerformed

    private void FlightAddToFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FlightAddToFrameActionPerformed
    }//GEN-LAST:event_FlightAddToFrameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADD;
    private javax.swing.JPanel AddPanel;
    private javax.swing.JPanel AdminPanel;
    private javax.swing.JTextField AirlineTx;
    private javax.swing.JPanel AirplanePanel;
    private javax.swing.JTextField AirportCity;
    private javax.swing.JTextField AirportCountry;
    private javax.swing.JTextField AirportId;
    private javax.swing.JTextField AirportLatitude;
    private javax.swing.JTextField AirportLongitude;
    private javax.swing.JTextField AirportName;
    private javax.swing.JComboBox<String> ArrivalLocationFlight;
    private javax.swing.JTextField BrandAirplaneTx;
    private javax.swing.JTextField CountryTx;
    private javax.swing.JTextField CountryUpdate;
    private javax.swing.JButton CreateAirplaneBtn;
    private javax.swing.JButton CreateFlightRegistration;
    private javax.swing.JButton CreateLocationRegistration;
    private javax.swing.JComboBox<String> DAY;
    private javax.swing.JComboBox<String> DAY1;
    private javax.swing.JButton DELAY;
    private javax.swing.JComboBox<String> DayUpdate;
    private javax.swing.JPanel DelayPanel;
    private javax.swing.JTextField DepartureDateFlight;
    private javax.swing.JComboBox<String> DepartureLocationFlight;
    private javax.swing.JTextField FirstNameTx;
    private javax.swing.JTextField FirstNameUpdate;
    private javax.swing.JComboBox<String> FlightAddToFrame;
    private javax.swing.JPanel FlightPanel;
    private javax.swing.JComboBox<String> HOUR;
    private javax.swing.JComboBox<String> HOURLLEGADA;
    private javax.swing.JComboBox<String> HOURSCALE;
    private javax.swing.JComboBox<String> HOURSdelay;
    private javax.swing.JComboBox<String> IDavion;
    private javax.swing.JTextField IdAddToFlight;
    private javax.swing.JTextField IdAirplaneTx;
    private javax.swing.JTextField IdFLight;
    private javax.swing.JTextField IdPassengerTx;
    private javax.swing.JTextField IdUpdate;
    private javax.swing.JTextField IndicatorTx;
    private javax.swing.JTextField IndicatorUpdate;
    private javax.swing.JTextField LastNameTx;
    private javax.swing.JTextField LastNameUpdate;
    private javax.swing.JPanel LocationPanel;
    private javax.swing.JComboBox<String> MINUTE;
    private javax.swing.JComboBox<String> MINUTELLEGADA;
    private javax.swing.JComboBox<String> MINUTESCALE;
    private javax.swing.JComboBox<String> MINUTESdelay;
    private javax.swing.JComboBox<String> MONTH;
    private javax.swing.JComboBox<String> MONTH1;
    private javax.swing.JTextField MaxCapacityTx;
    private javax.swing.JTextField ModelAirplaneTx;
    private javax.swing.JComboBox<String> MonthUpdate;
    private javax.swing.JTextField NumUpdate;
    private javax.swing.JTabbedPane PanelDeOpciones;
    private javax.swing.JPanel PassengerPanel;
    private javax.swing.JTextField PhoneTx;
    private javax.swing.JComboBox<String> PlaneFlight;
    private javax.swing.JButton REFRESH;
    private javax.swing.JButton REFRESHSHOWALLPASSENGERS;
    private javax.swing.JButton RefreshAllLocations;
    private javax.swing.JButton RefreshAllPlanes;
    private javax.swing.JButton RefreshShowAllFlights;
    private javax.swing.JButton RegisterPassengerBtn;
    private javax.swing.JComboBox<String> ScaleFlight;
    private javax.swing.JTable ShowAllFlights;
    private javax.swing.JTable ShowAllLocations;
    private javax.swing.JTable ShowAllPassengers;
    private javax.swing.JTable ShowAllPlanes;
    private javax.swing.JPanel ShowFlightPanel;
    private javax.swing.JPanel ShowLocationPanel;
    private javax.swing.JTable ShowMyFlights;
    private javax.swing.JPanel ShowPanel;
    private javax.swing.JPanel ShowPassengersPanel;
    private javax.swing.JPanel ShowPlanesPanel;
    private javax.swing.JButton UPADTE;
    private javax.swing.JPanel UpdatePanel;
    private javax.swing.JTextField YearTx;
    private javax.swing.JTextField YearUpdate;
    private javax.swing.JRadioButton administrator;
    private javax.swing.JButton jButton13;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private airport.PanelRound panelRound1;
    private airport.PanelRound panelRound2;
    private airport.PanelRound panelRound3;
    private javax.swing.JRadioButton user;
    private javax.swing.JComboBox<String> userSelect;
    // End of variables declaration//GEN-END:variables
}
