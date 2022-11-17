# EspressOS

## Overview
EspressOS is a mobile OS that supports the following features:
- Battery life
- Network connection
- Signal Strength
- Charging a phone
- Manage contact data
- Being able to copy contacts
- Delete contacts
- Add contacts
- Update contact details
- Copy contacts
- Manage messages
- Add messages
- Clear messages
- Get latest and oldest messages
- Run applications

## How to run the program?


## Factory Default
Every phone manufactured and installed with EspressOS OS. The default factory settings are:
- Phone is off
- Phone has battery life (25)
- Not connected to a network
- No Signal (signal strength is 0)

## Battery and Charging
The OS needs to keep track of the battery level and implement functions related to battery changing, charging and status. The battery level is represented as an integer between 0 and 100 inclusive. [0, 100]

## Network Connectivity and Signal
The network status has two parts. Network is connected and Signal strength. Signal strength is represented as a range between 0 and 5 inclusive [0,5]. 0 representing that the phone is not connected to a network while all numbers > 0 infer that the phone is connected to a network.

## Contact Management
The OS allows for the user to manage contacts by being able to search, remove and add contacts. The maximum number of contacts that can be stored on the device is 10 plus the owner contact.

## Contacts
Each contact has a:
- First Name
- Last Name
- Phone Number, cannot be less than 6 digits or greater than 14 digits.
- and Chat History

The fields First Name, Last Name and Phone Number can be updated by the user. A first and last name can be of any length and cannot be set to null. 

## Messaging
Messages are stored for each contact on the phone. Each contact can contain a maximum of 20 messages and once messages exceed that limit it will overwrite existing messages.

## Application (Feature)
EspressOS needs to support third party applications and allows a variety of third party applications to run on the system. Apps can implement different behaviour that will allow them to interact with different resources of the system. Currently only two behaviours are to be implemented, Background and Notify.

## Background Apps
Background apps will run in a loop until specified. They can exit by calling the exit() method associated with the BackgroundThread object which will allow it to end on its next loop.

## Notify Apps
Applications can utilise generate notifications and will interact with the EspressOS's own notification collection. Notify apps will create a notification that the user will be able to read.

## Tests
You can find unit tests in the home directory as well.
