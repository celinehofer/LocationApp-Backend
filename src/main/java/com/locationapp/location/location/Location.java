package com.locationapp.location.location;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Location {

        @Id
        @GeneratedValue
        private Long id;
        private String username;
        private String description;
        private Date targetDate;
        private String image;

        public Location() {

        }

    public Location(long id, String username, String description, Date targetDate) {
            super();
            this.id = id;
            this.username = username;
            this.description = description;
            this.targetDate = targetDate;
            this.image = image;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Date getTargetDate() {
            return targetDate;
        }

        public void setTargetDate(Date targetDate) {
            this.targetDate = targetDate;
        }

        public String getImage() {return image;}

        public void setImage(String image) { this.image = image; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Location other = (Location) obj;
            if (id != other.id)
                return false;
            return true;
        }
    }

