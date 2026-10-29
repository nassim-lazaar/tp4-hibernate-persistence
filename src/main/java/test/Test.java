package test;

import entities.Machine;
import entities.Salle;
import services.MachineService;
import services.SalleService;
import util.HibernateUtil;

import java.util.List;

/**
 * Application principale validant la persistance Hibernate et le modele relationnel.
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("     TP 4 : HIBERNATE ORM & PERSISTANCE DES DONNEES (SALLES ET MACHINES)        ");
        System.out.println("================================================================================");

        SalleService serviceSalle = new SalleService();
        MachineService serviceMachine = new MachineService();

        // 1. Creation des Salles
        System.out.println("\n[1] Insertion des salles d'ordinateurs...");
        Salle salleInfo = new Salle("LAB-INF-01", "Laboratoire Informatique & IA");
        Salle salleReseau = new Salle("LAB-RES-02", "Salle Réseaux & Télécoms");
        serviceSalle.create(salleInfo);
        serviceSalle.create(salleReseau);
        System.out.println(" -> Salle creee : " + salleInfo);
        System.out.println(" -> Salle creee : " + salleReseau);

        // 2. Creation des Machines associees
        System.out.println("\n[2] Insertion des machines reliees aux salles...");
        Machine m1 = new Machine("PC-DELL-501", "Dell OptiPlex", salleInfo);
        Machine m2 = new Machine("PC-HP-802", "HP EliteDesk", salleInfo);
        Machine m3 = new Machine("PC-LENOVO-303", "Lenovo ThinkCentre", salleReseau);
        Machine m4 = new Machine("PC-DELL-504", "Dell OptiPlex", salleReseau);

        serviceMachine.create(m1);
        serviceMachine.create(m2);
        serviceMachine.create(m3);
        serviceMachine.create(m4);
        System.out.println(" -> 4 machines inserees avec mapping @ManyToOne.");

        // 3. Requete HQL personnalisee : findByMarque
        System.out.println("\n[3] Execution d'une requete HQL : findByMarque('Dell OptiPlex')...");
        List<Machine> dells = serviceMachine.findByMarque("Dell OptiPlex");
        System.out.println(" -> " + dells.size() + " machine(s) Dell trouvee(s) :");
        for (Machine dm : dells) {
            System.out.println("    * Ref : " + dm.getRef() + " | Salle : " + dm.getSalle().getCode() + " (" + dm.getSalle().getLibelle() + ")");
        }

        // 4. Lecture globale de la couche de persistance
        System.out.println("\n[4] Liste complete des salles et machines enregistrees :");
        List<Salle> toutesLesSalles = serviceSalle.getAll();
        for (Salle s : toutesLesSalles) {
            System.out.println(" + Salle #" + s.getId() + " [" + s.getCode() + "] - " + s.getLibelle());
        }

        List<Machine> toutesLesMachines = serviceMachine.getAll();
        for (Machine m : toutesLesMachines) {
            System.out.println("   - Machine #" + m.getId() + " [" + m.getRef() + "] - Marque : " + m.getMarque() + " | Affectee a : " + m.getSalle().getCode());
        }

        System.out.println("\n================================================================================");
        System.out.println("      TEST DE PERSISTANCE HIBERNATE TERMINE AVEC SUCCES                         ");
        System.out.println("================================================================================");

        HibernateUtil.shutdown();
    }
}
