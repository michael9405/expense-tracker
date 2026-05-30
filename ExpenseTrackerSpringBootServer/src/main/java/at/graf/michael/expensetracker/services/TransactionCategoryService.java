package at.graf.michael.expensetracker.services;

import at.graf.michael.expensetracker.entities.TransactionCategory;
import at.graf.michael.expensetracker.entities.User;
import at.graf.michael.expensetracker.repositories.TransactionCategoryRepository;
import at.graf.michael.expensetracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TransactionCategoryService {
    private static final Logger logger = Logger.getLogger(TransactionCategoryService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionCategoryRepository transactionCategoryRepository;

    //region Post
    public TransactionCategory createTransactionCategory(int userId, String categoryName, String categoryColor) {
        logger.info("Create Transaction Category with user: " + userId);

        // find user with the userId
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) return null;

        TransactionCategory transactionCategory = new TransactionCategory();
        transactionCategory.setUser(user.get());
        transactionCategory.setCategoryName(categoryName);
        transactionCategory.setCategoryColor(categoryColor);

        return transactionCategoryRepository.save(transactionCategory);
    }

    //endregion
}
