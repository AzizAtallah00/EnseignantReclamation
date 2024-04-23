package tn.enicarthage.EnseignantReclamation.exception;

public enum ErrorCodes {
    ENSEIGNANT_NOT_FOUND (1000), // lorsque je cherche qlq chose dans la base données
    ENSEIGNANT_NOT_VALID(1001), //lorsque j'enregistre qlq chose dans la base de donnees

    PASSWORD_NOT_VALID (1002),
    RECLAMATION_NOT_FOUND (2000),
    RECLAMATION_NOT_VALID(2001),
    STAUS_NOT_VALID (2002),
    SALLE_NOT_FOUND (2003),

    TECHNICIEN_NOT_FOUND (3000), // lorsque je cherche qlq chose dans la base données
    TECHNICIEN_NOT_VALID(3001);


    private int code;

    ErrorCodes(int a)
    {
        this.code = a;
    }

    public int getCode()
    {
        return this.code;
    }

}
