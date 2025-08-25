import java.util.*;

// Notebook for prototyping a data-driven API service dashboard

// Data models
class Service {
    private String name;
    private String description;
    private int uptime;
    private double responseTime;

    public Service(String name, String description, int uptime, double responseTime) {
        this.name = name;
        this.description = description;
        this.uptime = uptime;
        this.responseTime = responseTime;
    }

    // Getters and setters
}

class Dashboard {
    private List<Service> services;

    public Dashboard() {
        this.services = new ArrayList<>();
    }

    public void addService(Service service) {
        this.services.add(service);
    }

    public List<Service> getServices() {
        return this.services;
    }
}

// Data storage
class DataService {
    private List<Service> services;

    public DataService() {
        this.services = new ArrayList<>();

        // Initialize sample data
        services.add(new Service("Service A", "Description for Service A", 99, 200.0));
        services.add(new Service("Service B", "Description for Service B", 98, 250.0));
        services.add(new Service("Service C", "Description for Service C", 97, 300.0));
    }

    public List<Service> getAllServices() {
        return this.services;
    }
}

// API service
class APIService {
    private DataService dataService;

    public APIService(DataService dataService) {
        this.dataService = dataService;
    }

    public List<Service> getServices() {
        return this.dataService.getAllServices();
    }
}

// Dashboard controller
class DashboardController {
    private APIService apiService;
    private Dashboard dashboard;

    public DashboardController(APIService apiService, Dashboard dashboard) {
        this.apiService = apiService;
        this.dashboard = dashboard;
    }

    public void loadServices() {
        List<Service> services = this.apiService.getServices();
        for (Service service : services) {
            this.dashboard.addService(service);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        DataService dataService = new DataService();
        APIService apiService = new APIService(dataService);
        Dashboard dashboard = new Dashboard();
        DashboardController controller = new DashboardController(apiService, dashboard);

        controller.loadServices();

        // Print dashboard data
        for (Service service : dashboard.getServices()) {
            System.out.println("Service: " + service.name + " - Uptime: " + service.uptime + "% - Response Time: " + service.responseTime + "ms");
        }
    }
}