package com.example.tp3;

import Beans.Etudiant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.ArrayList;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.System.out;


@WebServlet(name = "ScolariteManager", value = "/ScolariteManager")
public class ScolariteManager extends HttpServlet {
    ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
    ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
    ArrayList<String> modules = new ArrayList<String>();
    ArrayList<String> specialites = new ArrayList<String>();

    HashMap<String, HashMap<Etudiant,Double>> modulesEtudiants = new HashMap<String,HashMap<Etudiant,Double>>();
    HashMap<String,HashMap<Etudiant,Double>> modulesEtudiantstmp = new HashMap<String,HashMap<Etudiant,Double>>();
    HashMap<Etudiant,Double> noteEtudiants = new HashMap<Etudiant,Double>();
    HashMap<String,ArrayList<String>> modulesSpecialites = new HashMap<String,ArrayList<String>>();
    ArrayList<Etudiant> etudianttmp = new ArrayList<Etudiant>();

    @Override
    public void init() throws ServletException {
        super.init();
        Utilisateur admin = new Utilisateur("admin","admin");
        users.add(admin);
        setSpecialites();
        setModules();
        setModulesSpecialites();
    }
    protected void setSpecialites(){
        specialites.add("ICY");
        specialites.add("GCB");
        specialites.add("ME");
        specialites.add("GI");
    }

    public void setModules(){
        modules.add("Informatique");
        modules.add("Cybersécurité");
        modules.add("Maths");
        modules.add("Physique");
        modules.add("Humanités");
        modules.add("Electronique");
        modules.add("Chimie");
    }

    protected void setModulesSpecialites(){
        modulesSpecialites.put("ICY",new ArrayList<String>(Arrays.asList("Informatique","Maths","Cybersécurité")));
        modulesSpecialites.put("GCB",new ArrayList<String>(Arrays.asList("Maths","Physique","Chimie")));
        modulesSpecialites.put("ME",new ArrayList<String>(Arrays.asList("Maths","Physique","Humanités")));
        modulesSpecialites.put("GI",new ArrayList<String>(Arrays.asList("Maths","Physique","Electronique")));

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("specialites", specialites);
        request.getSession().setAttribute("modules", modules);
        request.getSession().setAttribute("etudiants", etudiants);
        request.getSession().setAttribute("modulesEtudiants",modulesEtudiants);
        request.getSession().setAttribute("etudianttmp", etudianttmp);
        request.getSession().setAttribute("modulesEtudiantstmp", modulesEtudiantstmp);

        out.println(request.getHeader("referer"));
        String lien = request.getHeader("referer"); // lien de la page précédente
        String[] lienSplit = lien.split("/"); // on split le lien pour récupérer le nom de la page

        String page = lienSplit[lienSplit.length-1]; // on récupère le nom de la page précédente
        String baselien = lienSplit[0] + "//" + lienSplit[2] + "/" +lienSplit[3] + "/"; // on récupère le lien de base
        out.println(baselien);
        out.println(page); // on affiche le nom de la page
        switch(page) {

            // ===== PAGE INSCRIPTION ===== //
            case "inscription.jsp":
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String spe = request.getParameter("specialite");
                etudiants.add(new Etudiant(nom,prenom,spe));
                out.println("Etudiant ajouté");
                response.sendRedirect(baselien + "Accueil.html");

                break;

            case "SaisieNote.jsp":
                String module = request.getParameter("module");
                String note = request.getParameter("note");
                String nomEtudiant = request.getParameter("nom");
                String prenomEtudiant = request.getParameter("prenom");
                if(!modulesEtudiants.containsKey(module)) {
                    for (Etudiant etudiant : etudiants) {
                        if (etudiant.getNom().equals(nomEtudiant) && etudiant.getPrenom().equals(prenomEtudiant)) {
                            for (String moduleSpecialite : modulesSpecialites.get(etudiant.getSpecialite())) {
                                if (moduleSpecialite.equals(module)) {
                                    HashMap<Etudiant, Double> etudiantNote = new HashMap<Etudiant, Double>();
                                    etudiantNote.put(etudiant, Double.parseDouble(note));
                                    modulesEtudiants.put(module, etudiantNote);
                                }
                            }
                        }
                    }
                }
                else{
                    for (Etudiant etudiant : etudiants) {
                        if (etudiant.getNom().equals(nomEtudiant) && etudiant.getPrenom().equals(prenomEtudiant)) {
                            for (String moduleSpecialite : modulesSpecialites.get(etudiant.getSpecialite())) {
                                if (moduleSpecialite.equals(module)) {
                                    HashMap<Etudiant, Double> etudiantNote = new HashMap<Etudiant, Double>();
                                    etudiantNote.put(etudiant, Double.parseDouble(note));
                                    modulesEtudiants.put(module, etudiantNote);
                                }
                            }
                        }
                    }}
                //Redirection vers la page d'accueil
                response.sendRedirect(baselien + "Accueil.html");

                break;


            // ===== PAGE AFFICHE SPECIALITES ===== //
            case "AfficheSpecialites.jsp":
                etudianttmp.clear();
                String specialite = request.getParameter("specialite");
                for (Etudiant etudiant : etudiants) {
                    out.println(etudiant.getSpecialite() + " "+ specialite);
                    if (etudiant.getSpecialite().equals(specialite)) {

                        etudianttmp.add(etudiant);
                    }
                }

                //Redirection vers la page d'accueil
                response.sendRedirect(baselien + "AfficheSpecialites.jsp");
                break;

            // ===== PAGE AFFICHE NOTES ===== //
            case "AfficheNotes.jsp":
                modulesEtudiantstmp.clear();
                String moduletmp = request.getParameter("module");
                for (String mod : modulesEtudiants.keySet()) {
                    if (mod.equals(moduletmp)) {
                        modulesEtudiantstmp.put(mod,modulesEtudiants.get(mod));
                    }
                }

                //Redirection vers la page d'accueil
                response.sendRedirect(baselien + "AfficheNotes.jsp");

                break;

            // ===== PAGE DYNAMIQUE RECHERCHE SPECIALITES ===== //
            case "AfficheSpecialitesDynamique.jsp":
                request.getSession().setAttribute("etudiants", etudiants);
                //Redirection vers la page d'accueil
                response.sendRedirect(baselien + "Accueil.html");


                break;

            // ===== PAGE DYNAMIQUE RECHERCHE NOTES ===== //
            case "AfficheNotesDynamique.jsp":

                //Redirection vers la page d'accueil
                response.sendRedirect(baselien + "Accueil.html");

                break;

            // ===== PAGE INDEX ===== //
            default :

                String login = request.getParameter("login");
                String password = request.getParameter("password");
                boolean verif = false;
                for(Utilisateur u : users){
                    if(u.verification(login,password) == 1){
                        verif = true;
                        //Redirection vers la page d'accueil
                        response.sendRedirect(baselien + "Accueil.html");
                    }
                }
                if(!verif){
                    //Redirection vers la page d'erreur
                    response.getWriter().println("login ou mot de passe invalide");
                }

                break;
        }

    }

}


