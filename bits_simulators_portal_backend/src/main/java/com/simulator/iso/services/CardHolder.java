package com.simulator.iso.services;

import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;

@Service
public class CardHolder implements Cloneable, Serializable {
    private static final long serialVersionUID = 7449770625551878435L;
    private static final String TRACK1_SEPARATOR = "^";
    private static final char TRACK2_SEPARATOR = '=';
    private static final int  BINLEN           =  6;
    private static final int  MINPANLEN        = 10;
    /**
     * Primary Account Number
     * @serial
     */
    protected String pan;
    /**
     * Expiration date (YYMM)
     * @serial
     */
    protected String exp;
    /**
     * Track2 trailler
     * @serial
     */
    protected String trailler;
    /**
     * Optional security code (CVC, CVV, Locale ID, wse)
     * @serial
     */
    protected String securityCode;
    /**
     * Track1 Data
     * @serial
     */
    protected String track1;

    /**
     * creates an empty CardHolder
     */
    public CardHolder() {
        super();
    }

    /**
     * creates a new CardHolder based on track2
     * @param track2 cards track2
     //* @exception InvalidCardException
     */
    public CardHolder (String track2) throws Exception
    {
        super();
        parseTrack2 (track2);
    }

    /**
     * creates a new CardHolder based on pan and exp
     //* @param track2 cards track2
     //* @exception InvalidCardException
     */
    public CardHolder (String pan, String exp) throws Exception
    {
        super();
        setPAN (pan);
        setEXP (exp);
    }

    /**
     * extract pan/exp/trailler from track2
     * @param s a valid track2
     * //@exception InvalidCardException
     */
    public void parseTrack2 (String s) throws Exception
    {
        if (s == null)
            throw new Exception ("null track2 data");
        int separatorIndex = s.replace ('D','=').indexOf(TRACK2_SEPARATOR);
        if ((separatorIndex > 0) && (s.length() > separatorIndex+4)) {
            pan = s.substring(0, separatorIndex);
            exp = s.substring(separatorIndex+1, separatorIndex+1+4);
            trailler = s.substring(separatorIndex+1+4);
        } else
            throw new Exception (s);
    }

    /**
     * @param track1 card's track1
     */
    public void setTrack1(String track1) {
        this.track1 = track1;
    }

    /**
     * @return the track1
     */
    public String getTrack1() {
        return track1;
    }

    /**
     * @return true if we have a track1
     */
    public boolean hasTrack1() {
        return (track1!=null);
    }

    /**
     * @return the Name written on the card (from track1)
     */
    public String getNameOnCard() {
        String name = null;
        if (track1!=null) {
            StringTokenizer st =
                    new StringTokenizer(track1, TRACK1_SEPARATOR);
            if (st.countTokens()<2)
                return null;
            st.nextToken(); // Skips the first token
            name = st.nextToken(); // This is the name
        }
        return name;
    }

    /**
     * @return reconstructed track2 or null
     */
    public String getTrack2() {
        if (hasTrack2())
            return pan + TRACK2_SEPARATOR + exp + trailler;
        else
            return null;
    }
    /**
     * @return true if we have a (may be valid) track2
     */
    public boolean hasTrack2() {
        return (pan != null && exp != null && trailler != null);
    }

    /**
     * assigns securityCode to this CardHolder object
     * @param securityCode
     */
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
    /**
     * @return securityCode (or null)
     */
    public String getSecurityCode() {
        return securityCode;
    }
    /**
     * @return true if we have a security code
     */
    public boolean hasSecurityCode() {
        return securityCode != null;
    }
    /**
     * @return trailler (may be null)
     */
    public String getTrailler() {
        return trailler;
    }
    /**
     * Set Trailler (used by OR-mapping stuff)
     * @param trailler
     */
    public void setTrailler (String trailler) {
        this.trailler = trailler;
    }

    /**
     * Sets Primary Account Number
     * @param pan
     //* @exception InvalidCardException
     */
    public void setPAN (String pan) throws Exception
    {
        if (pan.length() < MINPANLEN)
            throw new Exception (pan);
        this.pan = pan;
    }

    /**
     * @return Primary Account Number
     */
    public String getPAN () {
        return pan;
    }

    /**
     * Get Bank Issuer Number
     * @return bank issuer number
     */
    public String getBIN () {
        return pan.substring(0, BINLEN);
    }

    /**
     * Set Expiration Date
     * @param exp card expiration date
     //* @exception InvalidCardException
     */
    public void setEXP (String exp) throws Exception
    {
        if (exp.length() != 4)
            throw new Exception (pan+"/"+exp);
        this.exp = exp;
    }

    /**
     * Get Expiration Date
     * @return card expiration date
     */
    public String getEXP () {
        return exp;
    }

    public String formatDate (Date d, String pattern, TimeZone timeZone) {
        SimpleDateFormat df =
                (SimpleDateFormat) DateFormat.getDateTimeInstance();
        df.setTimeZone(timeZone);
        df.applyPattern(pattern);
        return df.format(d);
    }
    /**
     * Y2K compliant expiration check
     * @return true if card is expired (or invalid exp)
     */
    public boolean isExpired () {
        if (exp == null || exp.length() != 4)
            return true;
        String now = formatDate(new java.util.Date(), "yyyyMM",TimeZone.getDefault());
        try {
            int mm = Integer.parseInt(exp.substring(2));
            int aa = Integer.parseInt(exp.substring(0,2));
            if ((aa < 100) && (mm > 0) && (mm <= 12)) {
                String expDate = ((aa < 70) ? "20" : "19") + exp;
                if (expDate.compareTo(now) >= 0)
                    return false;
            }
        } catch (NumberFormatException e) { }
        return true;
    }
    public boolean isValidCRC () {
        return isValidCRC(this.pan);
    }
    public static boolean isValidCRC (String p) {
        int i, crc;

        int odd = p.length() % 2;

        for (i=crc=0; i<p.length(); i++) {
            char c = p.charAt(i);
            if (!Character.isDigit (c))
                return false;
            c = (char) (c - '0');
            if (i % 2 == odd)
                crc+=(c*2) >= 10 ? ((c*2)-9) : (c*2);
            else
                crc+=c;
        }
        return crc % 10 == 0;
    }

    /**
     * dumps CardHolder basic information<br>
     * by default we do not dump neither track1/2 nor securityCode
     * for security reasons.
     * @param p a PrintStream usually suplied by Logger
     * @param indent ditto
     //* @see BitsLib.util.Loggeable
     */
    public void dump (PrintStream p, String indent) {
        p.print (indent + "<CardHolder");
        if (hasTrack1())
            p.print (" trk1=\"true\"");

        if (hasTrack2())
            p.print (" trk2=\"true\"");

        if (hasSecurityCode())
            p.print (" sec=\"true\"");

        if (isExpired())
            p.print (" expired=\"true\"");

        p.println (">");
        p.println (indent + "  " + "<pan>" +pan +"</pan>");
        p.println (indent + "  " + "<exp>" +exp +"</exp>");
        p.println (indent + "</CardHolder>");
    }

    /**
     * @return ServiceCode (if available) or a String with three blanks
     */
    public String getServiceCode () {
        return (trailler != null && trailler.length() >= 3) ?
                trailler.substring (0, 3) :
                "   ";
    }
    public boolean seemsManualEntry() {
        return trailler == null ? true : (trailler.trim().length() == 0);
    }

    /**
     * compares two cardholder object<br>
     * based on PAN and EXP
     * @param obj a CardHolder instance
     * @return true if pan and exp matches
     */
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof CardHolder)) {
            CardHolder ch = (CardHolder) obj;
            if ( (pan != null) && (ch.pan != null) &&
                    (exp != null) && (ch.exp != null) &&
                    pan.equals (ch.pan) && exp.equals (ch.exp))
                return true;
        }
        return false;
    }

}
