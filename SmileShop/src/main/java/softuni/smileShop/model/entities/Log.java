//package softuni.demoprodgect.model.entities;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "logs")
//public class Log extends BaseEntity {
//    private User user;
//    private Product product;
//    private String action;
//    private LocalDateTime localDateTime;
//
//    public Log() {
//    }
//
//    @ManyToOne
//    public User getUser() {
//        return user;
//    }
//
//    public Log setUser(User user) {
//        this.user = user;
//        return this;
//    }
//
//    @ManyToOne
//    public Product getProduct() {
//        return product;
//    }
//
//    public Log setProduct(Product product) {
//        this.product = product;
//        return this;
//    }
//
//    @Column(name = "action", nullable = false)
//    public String getAction() {
//        return action;
//    }
//
//    public Log setAction(String action) {
//        this.action = action;
//        return this;
//    }
//
//    @Column(name = "date_time", nullable = false)
//    public LocalDateTime getLocalDateTime() {
//        return localDateTime;
//    }
//
//    public Log setLocalDateTime(LocalDateTime localDateTime) {
//        this.localDateTime = localDateTime;
//        return this;
//    }
//}
