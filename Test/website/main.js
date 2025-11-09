    // Application state
let currentUser = null;
let currentTenant = null;
let tenants = [];
let members = [];

// Mock data
const mockUsers = [
    { id: 1, name: 'John Doe', email: 'john@example.com', password: 'password123' },
    { id: 2, name: 'Jane Smith', email: 'jane@example.com', password: 'password123' },
    { id: 3, name: 'Bob Wilson', email: 'bob@example.com', password: 'password123' }
];

const mockTenants = [
    { id: 1, name: 'Acme Corp', description: 'Main company tenant', ownerId: 1 },
    { id: 2, name: 'Tech Startup', description: 'Development team', ownerId: 1 }
];

const mockMembers = [
    { id: 1, userId: 1, tenantId: 1, role: 'admin', name: 'John Doe', email: 'john@example.com' },
    { id: 2, userId: 2, tenantId: 1, role: 'member', name: 'Jane Smith', email: 'jane@example.com' },
    { id: 3, userId: 1, tenantId: 2, role: 'admin', name: 'John Doe', email: 'john@example.com' },
    { id: 4, userId: 3, tenantId: 2, role: 'member', name: 'Bob Wilson', email: 'bob@example.com' }
];

// Initialize app
document.addEventListener('DOMContentLoaded', function() {
    initializeApp();
});

function initializeApp() {
    // Load data from localStorage if available
    const savedUser = localStorage.getItem('currentUser');
    if (savedUser) {
        currentUser = JSON.parse(savedUser);
        showMainApp();
    }

    // Event listeners
    document.getElementById('loginForm').addEventListener('submit', handleLogin);
    document.getElementById('registerForm').addEventListener('submit', handleRegister);
    document.getElementById('createTenantForm').addEventListener('submit', handleCreateTenant);
    document.getElementById('inviteUserForm').addEventListener('submit', handleInviteUser);

    document.getElementById('showRegister').addEventListener('click', showRegister);
    document.getElementById('showLogin').addEventListener('click', showLogin);
    document.getElementById('logoutBtn').addEventListener('click', handleLogout);
    document.getElementById('createTenantBtn').addEventListener('click', () => openModal('createTenantModal'));
    document.getElementById('inviteUserBtn').addEventListener('click', () => openModal('inviteUserModal'));
    document.getElementById('backToTenantsBtn').addEventListener('click', showTenantSelection);
}

function handleLogin(e) {
    e.preventDefault();
    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;

    const user = mockUsers.find(u => u.email === email && u.password === password);
    if (user) {
        currentUser = user;
        localStorage.setItem('currentUser', JSON.stringify(user));
        showMainApp();
        showNotification('Login successful!', 'success');
    } else {
        showNotification('Invalid credentials!', 'error');
    }
}

function handleRegister(e) {
    e.preventDefault();
    const name = document.getElementById('registerName').value;
    const email = document.getElementById('registerEmail').value;
    const password = document.getElementById('registerPassword').value;

    // Check if user already exists
    if (mockUsers.find(u => u.email === email)) {
        showNotification('User already exists!', 'error');
        return;
    }

    // Create new user
    const newUser = {
        id: mockUsers.length + 1,
        name,
        email,
        password
    };
    mockUsers.push(newUser);
    currentUser = newUser;
    localStorage.setItem('currentUser', JSON.stringify(newUser));
    showMainApp();
    showNotification('Registration successful!', 'success');
}

function handleLogout() {
    currentUser = null;
    currentTenant = null;
    localStorage.removeItem('currentUser');
    showLogin();
    showNotification('Logged out successfully!', 'success');
}

function showLogin() {
    document.getElementById('loginScreen').classList.remove('hidden');
    document.getElementById('registerScreen').classList.add('hidden');
    document.getElementById('mainApp').classList.add('hidden');
}

function showRegister() {
    document.getElementById('loginScreen').classList.add('hidden');
    document.getElementById('registerScreen').classList.remove('hidden');
    document.getElementById('mainApp').classList.add('hidden');
}

function showMainApp() {
    document.getElementById('loginScreen').classList.add('hidden');
    document.getElementById('registerScreen').classList.add('hidden');
    document.getElementById('mainApp').classList.remove('hidden');
    document.getElementById('userName').textContent = `Welcome, ${currentUser.name}!`;
    loadTenants();
    showTenantSelection();
}

function showTenantSelection() {
    document.getElementById('tenantManagement').classList.add('hidden');
    document.querySelector('.tenant-selector').classList.remove('hidden');
}

function loadTenants() {
    tenants = mockTenants.filter(t => t.ownerId === currentUser.id);
    const tenantGrid = document.getElementById('tenantGrid');
    tenantGrid.innerHTML = '';

    tenants.forEach(tenant => {
        const tenantCard = document.createElement('div');
        tenantCard.className = 'tenant-card';
        tenantCard.innerHTML = `
            <div class="tenant-name">${tenant.name}</div>
            <div class="tenant-role">Owner</div>
            <div style="margin-top: 1rem;">
                <button class="btn btn-primary" onclick="selectTenant(${tenant.id})">Manage</button>
            </div>
        `;
        tenantGrid.appendChild(tenantCard);
    });
}

function selectTenant(tenantId) {
    currentTenant = tenants.find(t => t.id === tenantId);
    document.getElementById('tenantTitle').textContent = `${currentTenant.name} - Management`;
    document.querySelector('.tenant-selector').classList.add('hidden');
    document.getElementById('tenantManagement').classList.remove('hidden');
    loadMembers();
}

function loadMembers() {
    members = mockMembers.filter(m => m.tenantId === currentTenant.id);
    const tbody = document.getElementById('membersTableBody');
    tbody.innerHTML = '';

    members.forEach(member => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${member.name}</td>
            <td>${member.email}</td>
            <td><span class="role-badge role-${member.role}">${member.role}</span></td>
            <td>
                <div class="actions">
                    ${member.role === 'member' ? 
                        `<button class="btn btn-secondary" onclick="promoteUser(${member.id})">Promote</button>` : 
                        `<button class="btn btn-secondary" onclick="demoteUser(${member.id})">Demote</button>`
                    }
                    <button class="btn btn-danger" onclick="removeMember(${member.id})">Remove</button>
                </div>
            </td>
        `;
        tbody.appendChild(row);
    });

    updateStats();
}

function updateStats() {
    const memberCount = members.length;
    const adminCount = members.filter(m => m.role === 'admin').length;
    
    document.getElementById('memberCount').textContent = memberCount;
    document.getElementById('adminCount').textContent = adminCount;
    document.getElementById('inviteCount').textContent = '0'; // Mock pending invites
}

function handleCreateTenant(e) {
    e.preventDefault();
    const name = document.getElementById('tenantName').value;
    const description = document.getElementById('tenantDescription').value;

    const newTenant = {
        id: mockTenants.length + 1,
        name,
        description,
        ownerId: currentUser.id
    };

    mockTenants.push(newTenant);
    
    // Add owner as admin member
    const ownerMember = {
        id: mockMembers.length + 1,
        userId: currentUser.id,
        tenantId: newTenant.id,
        role: 'admin',
        name: currentUser.name,
        email: currentUser.email
    };
    mockMembers.push(ownerMember);

    closeModal('createTenantModal');
    loadTenants();
    showNotification('Tenant created successfully!', 'success');
}

function handleInviteUser(e) {
    e.preventDefault();
    const email = document.getElementById('inviteEmail').value;
    const role = document.getElementById('inviteRole').value;

    // Find user by email
    const user = mockUsers.find(u => u.email === email);
    if (!user) {
        showNotification('User not found!', 'error');
        return;
    }

    // Check if user is already a member
    if (members.find(m => m.userId === user.id)) {
        showNotification('User is already a member!', 'error');
        return;
    }

    // Add member
    const newMember = {
        id: mockMembers.length + 1,
        userId: user.id,
        tenantId: currentTenant.id,
        role,
        name: user.name,
        email: user.email
    };
    mockMembers.push(newMember);

    closeModal('inviteUserModal');
    loadMembers();
    showNotification('User invited successfully!', 'success');
}

function promoteUser(memberId) {
    const member = mockMembers.find(m => m.id === memberId);
    if (member) {
        member.role = 'admin';
        loadMembers();
        showNotification('User promoted to admin!', 'success');
    }
}

function demoteUser(memberId) {
    const member = mockMembers.find(m => m.id === memberId);
    if (member) {
        member.role = 'member';
        loadMembers();
        showNotification('User demoted to member!', 'success');
    }
}

function removeMember(memberId) {
    if (confirm('Are you sure you want to remove this member?')) {
        const index = mockMembers.findIndex(m => m.id === memberId);
        if (index > -1) {
            mockMembers.splice(index, 1);
            loadMembers();
            showNotification('Member removed successfully!', 'success');
        }
    }
}

function openModal(modalId) {
    document.getElementById(modalId).style.display = 'block';
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
    // Reset form
    const form = document.getElementById(modalId).querySelector('form');
    if (form) form.reset();
}

function showNotification(message, type) {
    // Remove existing notifications
    const existingNotifications = document.querySelectorAll('.notification');
    existingNotifications.forEach(n => n.remove());

    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;
    document.body.appendChild(notification);

    // Show notification
    setTimeout(() => notification.classList.add('show'), 100);

    // Hide notification after 3 seconds
    setTimeout(() => {
        notification.classList.remove('show');
        setTimeout(() => notification.remove(), 300);
    }, 3000);
}

// Close modal when clicking outside
window.onclick = function(event) {
    const modals = document.querySelectorAll('.modal');
    modals.forEach(modal => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });
};