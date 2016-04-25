Given a housemanager
When add house with street named Ulica and number 1 to manager
Then size of houses should return 1

When delete house with street named Ulica and number 1
Then size of houses should return 0
