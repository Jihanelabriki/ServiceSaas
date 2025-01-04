package org.example.gestiondesabonnementssaas.service;

import org.example.gestiondesabonnementssaas.entity.Subscription;
import org.example.gestiondesabonnementssaas.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    // Get all subscriptions
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    // Get a subscription by ID
    public Optional<Subscription> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    // Save a new subscription
    public Subscription saveSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    // Update a subscription
    public Subscription updateSubscription(Long id, Subscription subscription) {
        Subscription existingSubscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        existingSubscription.setServiceName(subscription.getServiceName());
        existingSubscription.setPrice(subscription.getPrice());
        existingSubscription.setDurationInMonths(subscription.getDurationInMonths());
        return subscriptionRepository.save(existingSubscription);
    }

    // Delete a subscription
    public void deleteSubscription(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new RuntimeException("Subscription not found");
        }
        subscriptionRepository.deleteById(id);
    }
}
