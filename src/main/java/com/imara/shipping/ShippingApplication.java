package com.imara.shipping;

import com.imara.shipping.config.AppConfig;
import com.imara.shipping.model.AdminPreference;
import com.imara.shipping.model.City;
import com.imara.shipping.service.AdminPreferenceService;
import com.imara.shipping.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class ShippingApplication {
	@Autowired
	protected CityService cityService;
	@Autowired
	protected AdminPreferenceService adminPreferenceService;
	@Autowired
	private AppConfig config;

	public static void main(String[] args) {
		SpringApplication.run(ShippingApplication.class, args);

	}

	private static City createCity(int id, String englishName, String arabicName) {
		City city = new City();
		city.setId(id);
		city.setEnglishName(englishName);
		city.setArabicName(arabicName);
		return city;
	}

	private static AdminPreference createPreference(int id, String name, String value) {
		AdminPreference adminPreference = new AdminPreference();
		adminPreference.setId(id);
		adminPreference.setName(name);
		adminPreference.setValue(value);
		return adminPreference;
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone(config.getTime_zone()));
		List<City> cities = new ArrayList<>();
		cities.add(createCity(1, "Riyadh", "الرياض"));
		cities.add(createCity(2, "Jeddah", "جدة"));
		cities.add(createCity(3, "Mecca", "مكة المكرمة"));
		cities.add(createCity(4, "Medina", "المدينة المنورة"));
		cities.add(createCity(5, "Dammam", "الدمام"));
		cities.add(createCity(6, "Taif", "الطائف"));
		cities.add(createCity(7, "Tabuk", "تبوك"));
		cities.add(createCity(8, "Buraidah", "بريدة"));
		cities.add(createCity(9, "Khobar", "الخبر"));
		cities.add(createCity(10, "Abha", "أبها"));
		cities.add(createCity(11, "Hail", "حائل"));
		cities.add(createCity(12, "Yanbu", "ينبع"));
		cities.add(createCity(13, "Najran", "نجران"));
		cities.add(createCity(14, "Jizan", "جيزان"));
		cities.add(createCity(15, "Al Jubail", "الجبيل"));
		cities.add(createCity(16, "Al Kharj", "الخرج"));
		cities.add(createCity(17, "Al Khobar", "الخبر"));
		cities.add(createCity(18, "Al Qatif", "القطيف"));
		cities.add(createCity(19, "Arar", "عرعر"));
		cities.add(createCity(20, "Dhahran", "الظهران"));
		cities.add(createCity(21, "Al Hufuf", "الهفوف"));
		cities.add(createCity(22, "Ras Tanura", "رأس تنورة"));
		cities.add(createCity(23, "Sakaka", "سكاكا"));
		cities.add(createCity(24, "Unaizah", "عنيزة"));

		List<AdminPreference> adminPreferences = new ArrayList<>();
		adminPreferences.add(createPreference(1, "SHIPMENT_RATE", "2.00"));
		adminPreferences.add(createPreference(2, "SHIPMENT_PERCENTAGE", "20"));
		adminPreferences.add(createPreference(3, "ADMIN_USERNAME", "admin"));
		adminPreferences.add(createPreference(4, "ADMIN_PASSWORD", "admin"));
		adminPreferences.add(createPreference(5, "ADMIN_TOKEN", ""));

		cityService.saveCities(cities);
		adminPreferenceService.savePreferences(adminPreferences);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}

			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				// Get the server directory
				String workingDir = System.getProperty("user.dir").replace("\\", "/");
				registry.addResourceHandler("/logos/**").addResourceLocations("file:///" + workingDir + "/logos/");
			}
		};
	}

}