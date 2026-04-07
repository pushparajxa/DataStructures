# Region
* It is a separate, physical geographical area around the world.
* Each region consists of a minimum of three physically separate AZs.
* There are one or more data centers per availability zone.
* **Virtual Private Cloud**  is defined at the level of region.
  * It may include one or many availability zones within the region
  * **Amazon Direct connectivity**
    * Using Amazon's provided Direct connectivity to connect your on premise network directly to 
      AWS (VPC) bypassing public internet to be protected from eavesdropping, congestion etc..

  * **Site to Site connectivity**
    * The primary site-to-site connectivity service provided by AWS is AWS Site-to-Site VPN. It 
    creates a secure, encrypted link between your on-premises network (like a data center or branch office) and your AWS resources using IPsec tunnels.
    * How it works
       * Encrypted Tunnels: It establishes a 'tunnel" over the public internet that protects your 
      data from being read by unauthorized parties while in transit.
       * High Availability: Each connection includes two tunnels by default, terminating in 
         different Availability Zones to ensure your connection stays up even if one tunnel fails.
         * Gateways: It connects a Customer Gateway (a physical device or software at your site) to 
           either a Virtual Private Gateway (for a single VPC) or a Transit Gateway (to connect multiple VPCs and sites)
* CIDR block size must be between 16 and 28. For example 10.0.0.0/16 to 10.0.0.0/28
* For a VPC 10.0.0.0/24
  * Reserved addresses are
    * 10.0.0.0 (-0) network address
    * 10.0.0.255 (255) broadcast
    * 10.0.0.1 reserved for router
    * 10.0.0.2 (+2) DNS
    * 10.0.0.3 future use
  * Route 53: Provides a complete DNS solution. Domain registrar, DNS management and traffic 
    routing.
  * Route 53 Private Resolver: This is a feature of Route 53 service,
    * Regional DNS service, query via VPC network + 2 address.
    * Provides recursive DNS queries for your VPCs.
    * It is available to all subnets in the VPC and across all availability zones
    * Resolves names for on premises environment as well as all cloud resources running within the 
      VPC. 
  * enableDnsHostnames: controls if VPC's support assigning public DNS names for instances with public IP addresses 
  * enableDneSupport: Controls if the VPC supports DNS resolution through the Amazon private 
      resolver within the VPC. 
  * Both enableDnsHostNames and enableDnsSupport can be enabled independently. 
* VPC and IGW (internet Gateway) have one-to-one mapping.

# EBS

EBS Volumes are replicated only with in the AZ. They are not replicated across AZ.
* It is not serverless. You must provision specific capacity and manage it.
* An EBS volume can be attached to multiple EC2 instances through multi-attach.
* For across AZ replication,
  * Application level replication
  * Task snapshots and store in S3 and then create new EBS volumes in another AZ based on that 
    snapshot.
  * Use Amazon EFS/FSx which are shared file systems designed for cross AZ data access.
* EBS Types
  * SSD -gp2 and gp3
    * General purpose disk io1 and io2 are provisioned IOPS volumes
      * These are high-performance storage volumes designed for mission-critical, i/O-intensive 
          workloads that require consistent, low-latency
        * Key Characteristics of Provisioned IOPS Volumes 
          * Custom Performance: Unlike standard volumes where performance may scale with size, you can specify ("provision" a specific level of performance in IOPS (Input/Output Operations Per Second). 
          * High Reliability: i02 Block Express volumes offer "five-nines" durability (99.999%), making them ideal for business-critical applications like SAP HANA or Oracle. 
          * Consistent Low Latency: These volumes are engineered to deliver sub-millisecond or single-digit millisecond latency. 
          * Multi-Attach Support: You can attach a single Provisioned IOPS volume to up to 16 Nitro-based EC2 instances simultaneously within the same Availability Zone. 
        * When to Use Them
          * Critical Databases: Large transactional databases (SQL Server, MySQL, PostgreSQL).
          * High-Traffic Applications: Workloads that require more than 16,000 OPS (the limit for 
               General Purpose gp3 volumes). 
          * Performance Consistency: Applications that cannot tolerate the performance variance 
               sometimes found in General Purpose SSDs.
  * HDD
  * Read and write workloads which do not need high IOPS (10 operations per second) or low latency.
  * Applications
    * Large scale data warehouses
    * Sequential data processing
    * Log processing
    * Streaming workloads.
  * Cold HDD sc1
    * Infrequently accessed data with low cost and low performance requirement.
    * Long term archive
    * Infrequently accessed data. 
  * Instance storage- attached directly to the underlying hypervisor
    * If an EC2 instance is moved from one Hypervisor to another, storage would be lost.
    * Temporary storage where data loss is acceptable
    * Applications
      * Cache drive for workloads
      * Fast temporary storage for high performance applications.
      
# EFS

EES:
→ It is server less meaning, storage services that are fully managed by the provider, requiring no manual provisioning, patching, or capacity management from the user.
* In this model, you typically pay only for what you use, and the service scales automatically 
with your data \

→ EFS Storage Classes
  * Standard 
      * Used for low latency applications.
  * Infrequent Access
  * Archive 
* Use cases
  * Web and Content management
  * Application Development
  * BigData and Analytics
  * Container storage
  * Multiple containers can use this volume so it acts as a shared storage environment between the 
    containers.
  * Content Distribution
    *  Real time collaboration on files to provide a central point for shared resources.
  * Migration
    * A stage area for migrating data into and out of AWS.

# S3(Object Storage)
→ Untike block storage, complete object is overwritten when you make changes and save it. \
→ If you have large scale un-structured which you need to store in a highly durable environment 
  and requires access by services in addition to just EC2, choose S3 \
→ If you need a service which provides fast access to data such as transactional data, or to 
  run an operating system, choose EBS \
→ If you need a solution which provides shared access to 
a set o files, choose EFS


# Cloudwatch Metrics

Namespace - Billing, Config, EC2, S3
* Dimensions - Per Instance Metrics
  *  Metrics:
       - NetworkIn
       - CPUCreditBalance
       - CPU Utilization
     
  ##  Cloudwatch Agent
      -> Custom metrics collection library.
  









































































