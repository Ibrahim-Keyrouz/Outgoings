package JSFclasses;

import entities.Outgoings;
import JSFclasses.util.JsfUtil;
import JSFclasses.util.JsfUtil.PersistAction;
import beans.OutgoingsFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ApplicationScoped;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@ManagedBean(name = "outgoingsController")
@ViewScoped
public class OutgoingsController implements Serializable {

    @EJB
    private beans.OutgoingsFacade ejbFacade;
    private List<Outgoings> items = null;
    private List<Outgoings> specific_items = null;
    private Outgoings selected;
    

    
    private BigDecimal selectedMemberid;  
      public BigDecimal getSelectedMemberid() {  
           return selectedMemberid;  
      }  
      public void setSelectedMemberid(BigDecimal selectedMemberid) {  
           this.selectedMemberid = selectedMemberid;  
      }  
    public OutgoingsController() {
        
    }

   

    public Outgoings getSelected() {
        return selected;
    }

    public void setSelected(Outgoings selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OutgoingsFacade getFacade() {
        return ejbFacade;
    }

    public Outgoings prepareCreate() {
        selected = new Outgoings();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OutgoingsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
           
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("OutgoingsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("OutgoingsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Outgoings> getItems() {
        if (items == null) {
            
         //  items = getFacade().getOutgoingsFromMember(selectedMemberid);
             items = getFacade().findAll();
        }
        return items;
    }
    
     public List<Outgoings> getSpecific_items() {
        if (specific_items == null) {
            
           specific_items = getFacade().getOutgoingsFromMember(selectedMemberid);
          //   specific_items = getFacade().findAll();
        }
        return specific_items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Outgoings> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Outgoings> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
   
    
    public BigInteger mult (BigInteger a , BigInteger b ) {
        return a.multiply(b) ; 
        
    }

    @FacesConverter(forClass = Outgoings.class)
    public static class OutgoingsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OutgoingsController controller = (OutgoingsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "outgoingsController");
            return controller.getFacade().find(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }
    
      

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Outgoings) {
                Outgoings o = (Outgoings) object;
                return getStringKey(o.getDocid());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Outgoings.class.getName()});
                return null;
            }
        }
@PostConstruct  
      void initialiseSession() {  
           FacesContext.getCurrentInstance().getExternalContext().getSession(true);  
      }  
    }
@PostConstruct  
      void initialiseSession() {  
           FacesContext.getCurrentInstance().getExternalContext().getSession(true);  
      }  
}
